import exercises.FilteringAggregation.sumEvenNumbers
import org.scalatest.funsuite.AnyFunSuite

class FilteringAggregationSpec extends AnyFunSuite {

  test("simple list") {
    assert(sumEvenNumbers(List(1, 2, 3, 4)) == 6)
  }

  test("no even numbers") {
    assert(sumEvenNumbers(List(1)) == 0)
  }
}
