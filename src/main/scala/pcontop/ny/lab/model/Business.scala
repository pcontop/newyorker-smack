package pcontop.ny.lab.model

import argonaut.Argonaut._
import argonaut._
import org.apache.spark.sql.types.{DoubleType, IntegerType, MapType, StringType, StructField, StructType}
case class BusinessData(
                         business_id: String,
                         name: String,
                         address: String,
                         city: String,
                         state: String,
                         postal_code: String,
                         latitude: Double,
                         longitude: Double,
                         stars: Double,
                         review_count: Int,
                         is_open: Int,
                         attributes: Option[Map[String, String]],
                         categories: String,
                         hours: Option[Map[String,String]]
                       )

class Business(val businessData:BusinessData) extends YelpContainer {

  override def values: Seq[Any] = {
    Seq (
      businessData.business_id,
      businessData.name,
      businessData.address,
      businessData.city,
      businessData.state,
      businessData.postal_code,
      businessData.latitude,
      businessData.longitude,
      businessData.stars,
      businessData.review_count,
      businessData.is_open,
      businessData.attributes.orNull,
      businessData.categories,
      businessData.hours.orNull
    )
  }

}

object Business {

  implicit def BusinessCodecJson: DecodeJson[BusinessData] =
    jdecode14L(BusinessData.apply)(
      "business_id",
      "name",
      "address",
      "city",
      "state",
      "postal_code",
      "latitude",
      "longitude",
      "stars",
      "review_count",
      "is_open",
      "attributes",
      "categories",
      "hours"
    )

  def apply(b:BusinessData) = new Business(b)

}