package pcontop.ny.lab

import java.io.InputStream

import scala.io.{BufferedSource, Source}

object TestUtil {

  def getStream(path:String): InputStream = getClass.getResourceAsStream(path)

  def fileSource (path:String): BufferedSource = {
    Source.fromInputStream(getStream(path))
  }


}
