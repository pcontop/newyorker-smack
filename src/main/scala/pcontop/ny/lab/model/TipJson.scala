package pcontop.ny.lab.model

import java.sql.Timestamp

import argonaut.Argonaut._
import argonaut._

case class TipJson(
                  user_id:String,
                  business_id: String,
                  text: String,
                  date: String,
                  compliment_count: Int
                ) extends YelpContainer

case class Tip(
                user_id:String,
                business_id: String,
                text: String,
                date: Option[Timestamp],
                compliment_count: Int
                  ) extends YelpContainer

object TipJson {

  import pcontop.ny.lab.util.YelpContainerUtils._

  implicit def CodecJson: DecodeJson[TipJson] =
    jdecode5L(TipJson.apply)(
      "user_id",
      "business_id",
      "text",
      "date",
      "compliment_count"
    )

  def toTip(tipJson: Option[TipJson]):Option[Tip] = {
    tipJson match {
      case None => None
      case Some(t) => Some(
        Tip(
          t.user_id,
          t.business_id,
          t.text,
          toTimestampOption(t.date),
          t.compliment_count
        )
      )
    }
  }

}
