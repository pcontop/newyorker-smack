package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.Review

object ReviewReader extends YelpReader[Review] {

  override def decodeOptionFromJson(entry: String): Option[Review] = {
    import pcontop.ny.lab.model.Review._
    entry.decodeOption[Review]
  }

  override def buildDF(rdd: RDD[Option[Review]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "review"
}
