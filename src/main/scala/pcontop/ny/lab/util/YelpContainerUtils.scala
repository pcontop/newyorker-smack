package pcontop.ny.lab.util

import java.sql.Timestamp
import java.text.{DateFormat, SimpleDateFormat}

object YelpContainerUtils {

  val dateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def toListString(line:String): List[String] = line match {
    case null => Nil
    case a: String => a.split(", ").toList
  }

  def toListTimestamp(line:String): List[Timestamp] = line match {
    case null => Nil
    case a:String => toListString(a) match {
      case Nil => Nil
      case b => b.map(toTimestampOption).filter(_.nonEmpty).map(_.get)
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
