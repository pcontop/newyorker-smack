package pcontop.ny.lab.model

import java.sql.Timestamp

import argonaut.Argonaut._
import argonaut._

case class ReviewJson(
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

case class Review(
                   review_id: String,
                   user_id: String,
                   business_id: String,
                   stars: Double,
                   useful: Int,
                   funny: Int,
                   cool: Int,
                   text: String,
                   date: Option[Timestamp]
                 ) extends YelpContainer


object ReviewJson {

  import pcontop.ny.lab.util.YelpContainerUtils._

  implicit def CodecJson: DecodeJson[ReviewJson] =
    jdecode9L(ReviewJson.apply)(
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

  def toReview(reviewJsonOpt: Option[ReviewJson]):Option[Review] = {
    if (reviewJsonOpt.isEmpty){
      None
    }else {
      val reviewJson = reviewJsonOpt.get
      Some(Review(
        reviewJson.review_id,
        reviewJson.user_id,
        reviewJson.business_id,
        reviewJson.stars,
        reviewJson.useful,
        reviewJson.funny,
        reviewJson.cool,
        reviewJson.text,
        toTimestampOption(reviewJson.date)
      )
      )
    }

  }

}

