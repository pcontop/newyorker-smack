package pcontop.ny.lab.reader

import org.apache.spark.SparkContext

object TestRun {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext()
    println(sc.parallelize(Array("Hello", "World")).collect().mkString(" "))
  }
}
