package pcontop.ny.lab.util

object CaseClassUtil {
  def ccToMap(cc: AnyRef): Map[String,Any] =
    (Map[String, Any]() /: cc.getClass.getDeclaredFields) {
      (a, f) =>
        f.setAccessible(true)
        a + (f.getName -> f.get(cc))
    }

}