package pcontop.ny.lab.model

import argonaut.Argonaut.jdecode5L
import argonaut.DecodeJson

case class Tip(
                  user_id:String,
                  business_id: String,
                  text: String,
                  date: String,
                  compliment_count: Int
                ) extends YelpContainer

object Tip {

  implicit def CodecJson: DecodeJson[Tip] =
    jdecode5L(Tip.apply)(
      "user_id",
      "business_id",
      "text",
      "date",
      "compliment_count"
    )

}
