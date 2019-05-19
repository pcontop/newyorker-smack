package pcontop.ny.lab.model

import argonaut.Argonaut._
import org.scalatest.FunSuite
import pcontop.ny.lab.TestUtil._


class UserSuite extends FunSuite{

  def getYelps(file:String): Seq[Option[User]]  =
  {
    val jsons = fileSource(file).getLines()
    jsons.map (json => {
      val yelp = json.decodeOption[User]
      assert(yelp != null)
      assert(yelp.nonEmpty)
      yelp
    }
    ).toSeq
  }

  test ("parse jsons") {
    val yelp = getYelps("/json/user/user1.json")
  }

}
