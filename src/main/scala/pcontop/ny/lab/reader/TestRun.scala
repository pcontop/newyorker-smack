package pcontop.ny.lab.reader

import org.apache.spark.SparkContext

/**
  * Test for checking the base integrity of the jar file and spark-cli image.
  * @author pcontop
  */
object TestRun {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext()
    println(sc.parallelize(Array("Hello", "World!")).collect().mkString(" "))
  }
}
