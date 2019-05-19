package pcontop.ny.lab.model

import argonaut.Argonaut._
import org.scalatest.FunSuite
import pcontop.ny.lab.TestUtil._


class ReviewSuite extends FunSuite{

  def getYelps(file:String): Seq[Option[ReviewJson]]  =
  {
    val jsons = fileSource(file).getLines()
    jsons.map (json => {
      val yelp = json.decodeOption[ReviewJson]
      assert(yelp != null)
      assert(yelp.nonEmpty)
      yelp
    }
    ).toSeq
  }

  test ("parse jsons") {
    val yelp = getYelps("/json/review/reviews1.json")
  }

}
