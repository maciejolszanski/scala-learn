package exercises

object DayUtils {

  def describeDay(day: String): String = {
    day match {
      case "Monday"               => "Start of the Week"
      case "Saturday" | "Sunday"  => "Weekend!"
      case _                      => "Just Another Day"
    }
  }

  def main(args: Array[String]): Unit = {
    println(describeDay("Monday"))
    println(describeDay("Saturday"))
    println(describeDay("Sunday"))
    println(describeDay("Friday"))
  }
}
