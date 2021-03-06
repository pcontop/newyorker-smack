package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.{Checkin, CheckinJson}

object CheckinReader extends YelpReader[Checkin] {

  override def decodeOptionFromJson(entry: String): Option[Checkin] = {
    import pcontop.ny.lab.model.CheckinJson._
    toCheckin(entry.decodeOption[CheckinJson])
  }

  override def buildDF(rdd: RDD[Option[Checkin]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "checkin"
}
