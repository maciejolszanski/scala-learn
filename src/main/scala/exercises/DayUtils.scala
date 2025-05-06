package exercises

object DayUtils {

  def describeDay(day: String): String = {
    val dayDescription: Map[String, String] = Map(
      "Monday"    -> "Start of the Week",
      "Saturday"  -> "Weekend!",
      "Sunday"    -> "Weekend!"
    )
    dayDescription.getOrElse(day, "Just Another Day")
  }

  def main(args: Array[String]): Unit = {
    println(describeDay("Monday"))
    println(describeDay("Saturday"))
    println(describeDay("Sunday"))
    println(describeDay("Friday"))
  }
}
