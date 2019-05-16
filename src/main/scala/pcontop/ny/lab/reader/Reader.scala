package pcontop.ny.lab.reader

import java.nio.charset._

import argonaut.Parse
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.spark.SparkContext
import org.apache.spark.input.PortableDataStream

import scala.util.Try

object Reader {
  def extractFiles(ps: PortableDataStream, n: Int = 1024) = Try {
    val tar = new TarArchiveInputStream(ps.open)
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

    val filesAsText = sc.binaryFiles(pathToFile).flatMapValues(x =>
      extractFiles(x).toOption).mapValues(_.map(decode())).flatMap(_._2)
    //No error treatment for now.
    val filesAsJson = filesAsText.map(Parse.parseOption(_)).filter(_.nonEmpty)


  }

}
