package pcontop.ny.lab.reader

import com.datastax.spark.connector.SomeColumns
import org.apache.spark.{SparkConf, SparkContext}
import com.datastax.spark.connector._


/**
  * Test to check the cassandra integration.
  * @author pcontop
  */
object TestCassandra {

  def main(args: Array[String]): Unit = {

    val cassandraDNS = args(0)
    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", cassandraDNS)
    val sc = new SparkContext(conf)

    val collection = sc.parallelize(Seq(("key1", "a"), ("key2","b")))
    collection.saveToCassandra("run_test", "yelp", SomeColumns("business_id", "name"))
  }

}
