package pcontop.ny.lab.reader

import argonaut.Argonaut._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import pcontop.ny.lab.model.{User, UserJson}

object UserReader extends YelpReader[User] {

  override def decodeOptionFromJson(entry: String): Option[User] = {
    import pcontop.ny.lab.model.UserJson._
    toUser(entry.decodeOption[UserJson])
  }

  override def buildDF(rdd: RDD[Option[User]], spark: SparkSession): DataFrame = {
    import spark.implicits._
    rdd.map(_.get).toDF
  }

  override val table:String = "user"
}
