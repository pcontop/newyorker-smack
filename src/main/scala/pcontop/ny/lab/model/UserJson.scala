package pcontop.ny.lab.model

import argonaut.Argonaut.jdecode22L
import argonaut.DecodeJson

case class UserJson(
                 user_id: String,
                 name: String,
                 review_count: Int,
                 yelping_since: String,
                 useful: Int,
                 funny: Int,
                 cool: Int,
                 elite: String,
                 friends: String,
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

case class User(
                     user_id: String,
                     name: String,
                     review_count: Int,
                     yelping_since: String,
                     useful: Int,
                     funny: Int,
                     cool: Int,
                     elite: String,
                     friends: List[String],
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
object UserJson {

  import pcontop.ny.lab.util.YelpContainerUtils._

  implicit def CodecJson: DecodeJson[UserJson] =
    jdecode22L(UserJson.apply)(
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

  def toUser(userJson: Option[UserJson]):Option[User] = {
    userJson match {
      case None => None
      case Some(u) => Some (
        User (
          u.user_id,
          u.name,
          u.review_count,
          u.yelping_since,
          u.useful,
          u.funny,
          u.cool,
          u.elite,
          toListString(u.friends),
          u.fans,
          u.average_stars,
          u.compliment_hot,
          u.compliment_more,
          u.compliment_profile,
          u.compliment_cute,
          u.compliment_list,
          u.compliment_note,
          u.compliment_plain,
          u.compliment_cool,
          u.compliment_funny,
          u.compliment_writer,
          u.compliment_photos
        )
      )
    }
  }

}

