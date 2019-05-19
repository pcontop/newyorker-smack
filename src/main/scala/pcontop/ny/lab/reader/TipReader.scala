package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.{Tip, TipJson}

object TipReader extends YelpReader[Tip] {

  override def decodeOptionFromJson(entry: String): Option[Tip] = {
    import pcontop.ny.lab.model.TipJson._
    toTip(entry.decodeOption[TipJson])
  }

  override def buildDF(rdd: RDD[Option[Tip]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "tip"
}
