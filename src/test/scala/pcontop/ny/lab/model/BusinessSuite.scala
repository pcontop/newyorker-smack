package pcontop.ny.lab.model


import org.scalatest.FunSuite
import pcontop.ny.lab.model.BusinessJson._
import argonaut._
import Argonaut._
import pcontop.ny.lab.TestUtil._


class BusinessSuite extends FunSuite{

  def getYelp(file:String): Option[BusinessJson]  =
  {
    val json = fileSource(file).mkString
    val yelp = json.decodeOption[BusinessJson]
    assert(yelp!=null)
    assert(yelp.nonEmpty)
    yelp
  }

  test ("parse json 0") {
    val yelp = getYelp("/json/business/business0.json")

    assert(yelp.get.business_id=="xvX2CttrVhyG2z1dFg_0xw")
    assert(yelp.get.latitude==33.4556129678)
    assert(yelp.get.review_count==3)
    assert(yelp.get.attributes.isEmpty)
    assert(yelp.get.hours.nonEmpty)

  }

  test ("parse json 1") {
    val yelp = getYelp("/json/business/business1.json")

    assert(yelp.get.business_id=="xvX2CttrVhyG2z1dFg_0xw")
    assert(yelp.get.latitude==33.4556129678)
    assert(yelp.get.review_count==3)
    assert(yelp.get.attributes.isEmpty)
    assert(yelp.get.hours.nonEmpty)
    assert(yelp.get.hours.get("Monday")=="8:0-17:0")
  }

  test ("parse json 2") {
    val yelp = getYelp("/json/business/business2.json")

    assert(yelp.get.business_id=="HhyxOkGAM07SRYtlQ4wMFQ")
    assert(yelp.get.latitude==35.1900119)
    assert(yelp.get.review_count==4)
    assert(yelp.get.attributes.nonEmpty)
    assert(yelp.get.attributes.get("BusinessAcceptsBitcoin")=="False")
    assert(yelp.get.hours.nonEmpty)
  }

}
