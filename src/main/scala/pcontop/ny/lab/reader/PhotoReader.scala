package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.Photo

object PhotoReader extends YelpReader[Photo] {

  override def decodeOptionFromJson(entry: String): Option[Photo] = {
    import pcontop.ny.lab.model.Photo._
    entry.decodeOption[Photo]
  }

  override def buildDF(rdd: RDD[Option[Photo]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "photo"
}
