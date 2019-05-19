package pcontop.ny.lab.util

import java.sql.Timestamp
import java.text.{DateFormat, SimpleDateFormat}

object YelpContainerUtils {

  val dateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def toListStringOption(line:String): Option[List[String]] = line match {
    case null => None
    case a: String => Some(a.split(", ").toList)
  }

  def toListTimestampOption(line:String): Option[List[Timestamp]] = line match {
    case null => None
    case a:String => toListStringOption(a) match {
      case None => None
      case Some(b) => Some(b.map(toTimestampOption).filter(_.nonEmpty).map(_.get))
    }
  }

  def toTimestampOption(entry:String): Option[Timestamp] = {
    try {
      Some(new Timestamp(dateFormat.parse(entry).getTime))
    } catch {
      case _:NumberFormatException | _:ArrayIndexOutOfBoundsException => None
    }
  }

}
