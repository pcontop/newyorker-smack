package pcontop.ny.lab.model

import argonaut.Argonaut._
import argonaut._

case class Review(
                   review_id: String,
                   user_id: String,
                   business_id: String,
                   stars: Double,
                   useful: Int,
                   funny: Int,
                   cool: Int,
                   text: String,
                   date: String
                 ) extends YelpContainer

object Review {

  implicit def CodecJson: DecodeJson[Review] =
    jdecode9L(Review.apply)(
      "review_id",
      "user_id",
      "business_id",
      "stars",
      "useful",
      "funny",
      "cool",
      "text",
      "date"
    )

}

