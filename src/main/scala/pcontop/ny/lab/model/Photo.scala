package pcontop.ny.lab.model

import argonaut.Argonaut._
import argonaut._

case class Photo(
    caption: String,
    photo_id: String,
    business_id: String,
    label: String
  ) extends YelpContainer


object Photo {

implicit def PhotoCodecJson: DecodeJson[Photo] =
jdecode4L(Photo.apply)(
"caption",
"photo_id",
"business_id",
"label"
)

}