package pcontop.ny.lab.reader

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.YelpContainer

abstract class YelpReader[T <: YelpContainer] extends Serializable {

  def decodeOptionFromJson(entry:String):Option[T]
  val table:String

  def main(args: Array[String]): Unit = {
    val pathToFile = args(0)
    val cassandraDNS = args(1)
    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", cassandraDNS)

    val spark = SparkSession.builder().config(conf).getOrCreate()
    val sc = spark.sparkContext

    println(s"Initializing processing of directory $pathToFile.")

    val filesAsText = sc.textFile(pathToFile)

    val jsonEntries = filesAsText.flatMap(_.split("\n"))

    //checkSome(jsonEntries)

    val yelps = jsonEntries.map(decodeOptionFromJson).filter(_.nonEmpty )

    //checkSome(yelps)

    val yelpDF = buildDF(yelps, spark)

    yelpDF.write
      .mode("append")
      .format("org.apache.spark.sql.cassandra")
      .options(Map( "table" ->  table, "keyspace" -> "run_test"))
      .save()

    println("Finished processing.")

  }

  def checkSome(jsons:RDD[_]):Unit = {
    val sample = jsons.take(10)
    println("**** Sample:")
    sample.foreach(println)
  }

  def buildDF (rdd:RDD[Option[T]], spark:SparkSession):DataFrame

}
