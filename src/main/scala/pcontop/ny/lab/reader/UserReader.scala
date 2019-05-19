package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.User

object UserReader extends YelpReader[User] {

  override def decodeOptionFromJson(entry: String): Option[User] = {
    import pcontop.ny.lab.model.User._
    entry.decodeOption[User]
  }

  override def buildDF(rdd: RDD[Option[User]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "user"
}
