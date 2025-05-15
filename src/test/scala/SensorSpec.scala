import exercises.sensor.SensorReading
import exercises.sensor.SensorProcessor
import org.scalatest.funsuite.AnyFunSuite

class SensorSpec extends AnyFunSuite {
  val inputReadings1: List[SensorReading] = List(
    SensorReading(1, -1.0),
    SensorReading(2, 23.1),
    SensorReading(3, 26.0),
    SensorReading(4, 45.4),
    SensorReading(5, 0.0)
  )

  test("filtering") {
    val actualResult = SensorProcessor.filterReadings(inputReadings1, 24.0)
    val expectedResult = List(
      SensorReading(3, 26.0),
      SensorReading(4, 45.4)
    )
    assert(actualResult.toSet == expectedResult.toSet)
  }

  val inputReadings2: List[SensorReading] = List(
    SensorReading(1, -1.0),
    SensorReading(2, 0.0),
    SensorReading(3, 4.0),
  )

  test("average") {
    val actualResult = SensorProcessor.avgReadings(inputReadings2)
    val expectedResult = 1.0
    assert(actualResult == expectedResult)
  }

  test("average v2") {
    val actualResult = SensorProcessor.avgReadings_v2(inputReadings2)
    val expectedResult = 1.0
    assert(actualResult == expectedResult)
  }

  test("average empty list") {
    val actualResult = SensorProcessor.avgReadings(List())
    val expectedResult = 0.0
    assert(actualResult == expectedResult)
  }

  test("average empty list v2") {
    val actualResult = SensorProcessor.avgReadings_v2(List())
    val expectedResult = 0.0
    assert(actualResult == expectedResult)
  }
}
