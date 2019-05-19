package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.Business

object BusinessReader extends YelpReader[Business] {

  override def decodeOptionFromJson(entry: String): Option[Business] = {
    import pcontop.ny.lab.model.Business._
    entry.decodeOption[Business]
  }

  override def buildDF(rdd: RDD[Option[Business]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "business"
}
