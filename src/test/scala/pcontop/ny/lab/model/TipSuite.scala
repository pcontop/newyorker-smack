package pcontop.ny.lab.model

import argonaut.Argonaut._
import org.scalatest.FunSuite
import pcontop.ny.lab.TestUtil._


class TipSuite extends FunSuite{

  def getTips(file:String): Seq[Option[Tip]]  =
  {
    val jsons = fileSource(file).getLines()
    jsons.map (json => {
      val yelp = json.decodeOption[Tip]
      assert(yelp != null)
      assert(yelp.nonEmpty)
      yelp
    }
    ).toSeq
  }

  test ("parse jsons") {
    val yelp = getTips("/json/tip/tips1.json")
  }

}
