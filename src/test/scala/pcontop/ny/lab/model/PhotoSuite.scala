package pcontop.ny.lab.model

import argonaut.Argonaut._
import org.scalatest.FunSuite
import pcontop.ny.lab.TestUtil._
import pcontop.ny.lab.model.Photo._


class PhotoSuite extends FunSuite{

  def getPhotos(file:String): Seq[Option[Photo]]  =
  {
    val jsons = fileSource(file).getLines()
    jsons.map (json => {
      val yelp = json.decodeOption[Photo]
      assert(yelp != null)
      assert(yelp.nonEmpty)
      yelp
    }
    ).toSeq
  }

  test ("parse jsons") {
    val yelp = getPhotos("/json/photos/photos1.json")

  }

}
