package pcontop.ny.lab.model

import argonaut.Argonaut._
import org.scalatest.FunSuite
import pcontop.ny.lab.TestUtil._


class CheckinSuite extends FunSuite{

  def getYelps(file:String): Seq[Option[CheckinJson]]  =
  {
    val jsons = fileSource(file).getLines()
    jsons.map (json => {
      val yelp = json.decodeOption[CheckinJson]
      assert(yelp != null)
      assert(yelp.nonEmpty)
      yelp
    }
    ).toSeq
  }

  test ("parse jsons") {
    val yelp = getYelps("/json/checkin/checkins1.json")
  }

  test("toCheckin") {
    val checkins = getYelps("/json/checkin/checkins1.json").map(CheckinJson.toCheckin)
    checkins.map(_.get).foreach(checkin => assert(checkin.date.nonEmpty))
    assert(checkins.exists(_.nonEmpty))
  }

}
