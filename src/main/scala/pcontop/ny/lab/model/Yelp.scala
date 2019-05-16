package pcontop.ny.lab.model

case class Yelp (
                  business_id: String,
                  name: String,
                  address: String,
                  city: String,
                  state: String,
                  postal_code: String,
                  latitude: Double,
                  longitude: Double,
                  stars: Double,
                  review_count: Int,
                  is_open: Int,
                  attributes: Map[String,String],
                  categories: List[String],
                  hours: Map[String,String]
                )
