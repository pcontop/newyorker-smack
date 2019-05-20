package pcontop.ny.lab.model

import java.sql.Timestamp

import argonaut.Argonaut._
import argonaut._

case class CheckinJson(
                  business_id:String,
                  date: String
                ) extends YelpContainer

case class Checkin(
                    business_id:String,
                    date: List[Timestamp]
                  ) extends YelpContainer

object CheckinJson {

  import pcontop.ny.lab.util.YelpContainerUtils._

  implicit def CodecJson: DecodeJson[CheckinJson] =
    jdecode2L(CheckinJson.apply)(
      "business_id",
      "date"
    )

  def toCheckin(checkinJsonOpt: Option[CheckinJson]): Option[Checkin] = {
    if (checkinJsonOpt.isEmpty) {
      None
    } else {
      val checkinJson = checkinJsonOpt.get
      Some(Checkin(
        checkinJson.business_id,
        toListTimestamp(checkinJson.date)
      ))
    }
  }

}