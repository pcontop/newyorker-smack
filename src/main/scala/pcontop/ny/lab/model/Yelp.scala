package pcontop.ny.lab.model

import argonaut.Argonaut._
import argonaut._


object Yelp {

  case class Yelp(
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


  implicit def YelpCodecJson: DecodeJson[Yelp] =
    jdecode14L(Yelp.apply)(
      yelpColumns(0),
      yelpColumns(1),
      yelpColumns(2),
      yelpColumns(3),
      yelpColumns(4),
      yelpColumns(5),
      yelpColumns(6),
      yelpColumns(7),
      yelpColumns(8),
      yelpColumns(9),
      yelpColumns(10),
      yelpColumns(11),
      yelpColumns(12),
      yelpColumns(13)
    )


  val yelpColumns:Array[String] = Array (
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


  def yelpValues(yelp:Yelp):
  (
    String,
      String,
      String,
      String,
      String,
      String,
      Double,
      Double,
      Double,
      Int,
      Int,
      Map[String, String],
      String,
      Map[String,String]
    ) = {
    (
      yelp.business_id,
      yelp.name,
      yelp.address,
      yelp.city,
      yelp.state,
      yelp.postal_code,
      yelp.latitude,
      yelp.longitude,
      yelp.stars,
      yelp.review_count,
      yelp.is_open,
      yelp.attributes.orNull,
      yelp.categories,
      yelp.hours.orNull
    )
  }

}

