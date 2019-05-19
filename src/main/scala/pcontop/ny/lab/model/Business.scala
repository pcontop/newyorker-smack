package pcontop.ny.lab.model

import argonaut.Argonaut._
import argonaut._
case class Business(
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
                       ) extends YelpContainer

object Business {

  implicit def BusinessCodecJson: DecodeJson[Business] =
    jdecode14L(Business.apply)(
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
}