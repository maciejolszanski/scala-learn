import exercises.DayUtils.describeDay
import org.scalatest.funsuite.AnyFunSuite

class DescribeDaySpec extends AnyFunSuite {
  
  test("returns 'Start of the Week' for Monday") {
    assert(describeDay("Monday") == "Start of the Week")
  }

  test("returns 'Weekend!' for Saturday") {
    assert(describeDay("Saturday") == "Weekend!")
  }

  test("returns 'Weekend!' for Sunday") {
    assert(describeDay("Sunday") == "Weekend!")
  }

  test("returns 'Just Another Day' for Wednesday") {
    assert(describeDay("Wednesday") == "Just Another Day")
  }

  test("is case-sensitive (Monday != monday)") {
    assert(describeDay("monday") == "Just Another Day")
  }
}