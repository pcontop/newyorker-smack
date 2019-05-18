package pcontop.ny.lab.reader

import java.nio.charset._

import pcontop.ny.lab.model.Yelp._
import argonaut._
import Argonaut._
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.spark.SparkContext
import org.apache.spark.input.PortableDataStream
import org.apache.spark.rdd.RDD
import pcontop.ny.lab.util.CaseClassUtil
import com.datastax.spark.connector._


import scala.util.Try

object Reader {
  def extractFiles(ps: PortableDataStream, n: Int = 1024) = Try {
    val tar = new TarArchiveInputStream(new GzipCompressorInputStream(ps.open))
    Stream.continually(Option(tar.getNextTarEntry))
      // Read until next exntry is null
      .takeWhile(_.isDefined)
      // flatten
      .flatten
      // Drop directories
      .filter(!_.isDirectory)
      .map(e => {
        Stream.continually {
          // Read n bytes
          val buffer = Array.fill[Byte](n)(-1)
          val i = tar.read(buffer, 0, n)
          (i, buffer.take(i))}
          // Take as long as we've read something
          .takeWhile(_._1 > 0)
          .flatMap(_._2)
          .toArray})
      .toArray
  }

  def decode(charset: Charset = StandardCharsets.UTF_8)(bytes: Array[Byte]) =
    new String(bytes, StandardCharsets.UTF_8)

  def main(args: Array[String]): Unit = {
    val pathToFile = args(0)

    val sc = new SparkContext()

    println(s"Initializing processing of directory $pathToFile.")

    val files = sc.binaryFiles(pathToFile)

    println(s"Files found: ${files.count()}")

    val filesAsText = files.
      flatMapValues(x =>
      extractFiles(x).toOption).mapValues(_.map(decode())).flatMap(_._2)

    println(s"Files processed: ${filesAsText.count()}")
    //No error treatment for now.
    val jsonEntries = filesAsText.flatMap(_.split("\n"))

    //checkSomeJsons(jsonEntries)

    val yelps = jsonEntries.map(_.decodeOption[Yelp]).filter(_.nonEmpty)

    checkSomeYelps(yelps)

    val valuesRDD = yelps.map(yelp => CaseClassUtil.ccToMap(yelp).toList)

    valuesRDD.saveAsCassandraTable(
      "run_test",
      "yelp",
      SomeColumns(yelpColumns.map(ColumnName(_)): _*)
    )

    println("Finished processing.")

  }

  def checkSomeYelps(filesStream:RDD[Option[Yelp]]): Unit ={
    val sample = filesStream.take(10)
    println("**** Sample Yelps:")
    sample.foreach(println)
    println(s"Non-empty from sample: ${sample.count(_.nonEmpty)}")
  }

  def checkSomeJsons(jsons:RDD[String]):Unit = {
    val sample = jsons.take(10)
    println("**** Sample Jsons:")
    sample.foreach(println)
  }

}
