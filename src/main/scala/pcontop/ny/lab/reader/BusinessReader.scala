package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.{Business, BusinessData}

object BusinessReader extends YelpReader[Business] {

  override def decodeOptionFromJson(entry: String): Option[Business] = {
    import pcontop.ny.lab.model.Business._
    val businessData = entry.decodeOption[BusinessData]
    if (businessData.isEmpty){
      None
    } else {
      Some(Business(businessData.get))
    }
  }

  override def buildDF(rdd: RDD[Option[Business]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get.businessData).toDF
  }

  override val table:String = "business"
}
