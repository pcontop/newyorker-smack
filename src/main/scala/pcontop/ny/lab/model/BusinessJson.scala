package pcontop.ny.lab.model

import argonaut.Argonaut._
import argonaut._

case class BusinessJson(
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

case class Business (
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
                      categories: Option[List[String]],
                      hours: Option[Map[String,String]]
                    ) extends YelpContainer

object BusinessJson {
  import pcontop.ny.lab.util.YelpContainerUtils._

  implicit def BusinessCodecJson: DecodeJson[BusinessJson] =
    jdecode14L(BusinessJson.apply)(
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

  def toBusiness(businessJsonOpt: Option[BusinessJson]):Option[Business] = {
    if (businessJsonOpt.isEmpty) {
      None
    } else {
      val businessJson = businessJsonOpt.get
      Some(Business(
        businessJson.business_id,
        businessJson.name,
        businessJson.address,
        businessJson.city,
        businessJson.state,
        businessJson.postal_code,
        businessJson.latitude,
        businessJson.longitude,
        businessJson.stars,
        businessJson.review_count,
        businessJson.is_open,
        businessJson.attributes,
        toListStringOption(businessJson.categories),
        businessJson.hours
      )
      )
    }
  }
}