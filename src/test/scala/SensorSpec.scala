import exercises.sensor.SensorReading
import exercises.sensor.SensorProcessor
import org.scalatest.funsuite.AnyFunSuite

class SensorSpec extends AnyFunSuite {
  val inputReadings: List[SensorReading] = List(
    SensorReading(1, -1.0),
    SensorReading(2, 23.1),
    SensorReading(3, 26.0),
    SensorReading(4, 45.4),
    SensorReading(5, 0.0)
  )

  test("filtering") {
    val actualResult = SensorProcessor.filterReadings(inputReadings, 24.0)
    val expectedResult = List(
      SensorReading(3, 26.0),
      SensorReading(4, 45.4)
    )
    assert(actualResult.toSet == expectedResult.toSet)
  }
}
