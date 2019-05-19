package pcontop.ny.lab.reader

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.YelpContainer

/**
  * Template for all the json readers.
  * @tparam T The type of the YelpContainer that is going to be associated with the implementation class.
  * @author pcontop
  */
abstract class YelpReader[T <: YelpContainer] extends Serializable {

  /**
    * Defines the target table, on the
    */
  val table:String

  val keyspace:String = "run_test"

  /**
    * Method that transforms a json entry into an YelpContainer object.
    * @param entry A json in text format.
    * @return An implementation Case Class of YelpContainer
    */
  def decodeOptionFromJson(entry:String):Option[T]

  /**
    * Method that builds the DF from an RDD containing a case class that implements YelpContainer
    * @param rdd The RDD with the Case Class
    * @param spark The spark session.
    * @return A DataFrame transformed from the RDD.
    */
  def buildDF (rdd:RDD[Option[T]], spark:SparkSession):DataFrame

  /**
    * Runs the process. Opens the file, splits the json entries (one per line)
    * parses the jsons and then saves them to the Cassandra table.
    * @param args <path to file> <cassandra docker dns>
    */
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
      .options(Map( "table" ->  table, "keyspace" -> keyspace))
      .save()

    println("Finished processing.")

  }

  def checkSome(jsons:RDD[_]):Unit = {
    val sample = jsons.take(10)
    println("**** Sample:")
    sample.foreach(println)
  }

}
