package pcontop.ny.lab.model

import argonaut.Argonaut._
import org.scalatest.FunSuite
import pcontop.ny.lab.TestUtil._


class CheckinSuite extends FunSuite{

  def getYelps(file:String): Seq[Option[Checkin]]  =
  {
    val jsons = fileSource(file).getLines()
    jsons.map (json => {
      val yelp = json.decodeOption[Checkin]
      assert(yelp != null)
      assert(yelp.nonEmpty)
      yelp
    }
    ).toSeq
  }

  test ("parse jsons") {
    val yelp = getYelps("/json/checkin/checkins1.json")
  }

}
