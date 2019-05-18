package pcontop.ny.lab.reader

import java.nio.charset._

import argonaut.Argonaut._
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.spark.SparkConf
import org.apache.spark.input.PortableDataStream
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import pcontop.ny.lab.model.Yelp._

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
    val cassandraDNS = args(1)
    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", cassandraDNS)

    val spark = SparkSession.builder().config(conf).getOrCreate()
    val sc = spark.sparkContext

    println(s"Initializing processing of directory $pathToFile.")

    val files = sc.binaryFiles(pathToFile)

    println(s"Files found: ${files.count()}")

    val filesAsText = files.
      flatMapValues(x =>
      extractFiles(x).toOption).mapValues(_.map(decode())).flatMap(_._2)

    println(s"Files processed: ${filesAsText.count()}")
    //No error treatment for now.
    val jsonEntries = filesAsText.flatMap(_.split("\n"))

    //checkSome(jsonEntries)

    val yelps = jsonEntries.map(_.decodeOption[Yelp]).filter(_.nonEmpty).map(_.get)

    //checkSome(yelps)

    val valuesRDD = yelps.map(yelpValues)

    //checkSome(valuesRDD)

    import spark.implicits._
    val yelpDF = valuesRDD.toDF(yelpColumns:_*)

    yelpDF.write
      .mode("append")
      .format("org.apache.spark.sql.cassandra")
      .options(Map( "table" -> "yelp", "keyspace" -> "run_test"))
      .save()

    println("Finished processing.")

  }

  def checkSome(jsons:RDD[_]):Unit = {
    val sample = jsons.take(10)
    println("**** Sample:")
    sample.foreach(println)
  }

}
