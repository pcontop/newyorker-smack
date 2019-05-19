package pcontop.ny.lab.model

import argonaut.Argonaut.jdecode22L
import argonaut.DecodeJson

case class User(
                 user_id: String,
                 name: String,
                 review_count: Int,
                 yelping_since: String,
                 useful: Int,
                 funny: Int,
                 cool: Int,
                 elite: String,
                 friends: String, /* TODO - Make it list */
                 fans: Int,
                 average_stars: Double,
                 compliment_hot: Int,
                 compliment_more: Int,
                 compliment_profile: Int,
                 compliment_cute: Int,
                 compliment_list: Int,
                 compliment_note: Int,
                 compliment_plain: Int,
                 compliment_cool: Int,
                 compliment_funny: Int,
                 compliment_writer: Int,
                 compliment_photos: Int
               ) extends YelpContainer

object User {

  implicit def CodecJson: DecodeJson[User] =
    jdecode22L(User.apply)(
      "user_id",
      "name",
      "review_count",
      "yelping_since",
      "useful",
      "funny",
      "cool",
      "elite",
      "friends",
      "fans",
      "average_stars",
      "compliment_hot",
      "compliment_more",
      "compliment_profile",
      "compliment_cute",
      "compliment_list",
      "compliment_note",
      "compliment_plain",
      "compliment_cool",
      "compliment_funny",
      "compliment_writer",
      "compliment_photos"
    )

}

