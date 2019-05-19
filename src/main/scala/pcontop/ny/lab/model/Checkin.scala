package pcontop.ny.lab.model

import argonaut.Argonaut.jdecode2L
import argonaut.DecodeJson

case class Checkin(
                  business_id:String,
                  date: String
                ) extends YelpContainer
//TODO - make dates here to list (another container?)

object Checkin {

  implicit def CodecJson: DecodeJson[Checkin] =
    jdecode2L(Checkin.apply)(
      "business_id",
      "date"
    )

}