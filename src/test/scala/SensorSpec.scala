import exercises.sensor.SensorReading
import exercises.sensor.SensorProcessor
import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDateTime

class SensorSpec extends AnyFunSuite {
  val timestamp = LocalDateTime.of(2025, 1, 1, 12, 0, 0)
  val inputReadings1: List[SensorReading] = List(
    SensorReading("sensor", -1.0, timestamp),
    SensorReading("sensor", 23.1, timestamp),
    SensorReading("sensor", 26.0, timestamp),
    SensorReading("sensor", 45.4, timestamp),
    SensorReading("sensor", 0.0, timestamp)
  )

  test("filtering") {
    val actualResult = SensorProcessor.filterReadings(inputReadings1, 24.0)
    val expectedResult = List(
      SensorReading("sensor", 26.0, timestamp),
      SensorReading("sensor", 45.4, timestamp)
    )
    assert(actualResult.toSet == expectedResult.toSet)
  }

  val inputReadings2: List[SensorReading] = List(
    SensorReading("sensor", -1.0, timestamp),
    SensorReading("sensor", 0.0, timestamp),
    SensorReading("sensor", 4.0, timestamp),
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

  test("sensor to string") {
    val timestamp = LocalDateTime.of(2025, 1, 1, 12, 0, 0)
    val reading = SensorReading("sensor_1", 25.1, timestamp)
    val expectedOutput = "2025-01-01 12:00:00, Sensor: sensor_1, Temperature: 25.1 K"
    assert (expectedOutput == reading.toString())
  }

  test("is reading recent True") {
    val timestampNow = LocalDateTime.of(2025, 1, 1, 12, 0, 0)
    val timestampSensor = LocalDateTime.of(2025, 1, 1, 11, 55, 0)
    val thresholdMinutes = 10
    val reading = SensorReading("sensor_1", 25.1, timestampSensor)

    assert(reading.isRecent(timestampNow, thresholdMinutes))
  }

  test("is reading recent False") {
    val timestampNow = LocalDateTime.of(2025, 1, 1, 12, 0, 0)
    val timestampSensor = LocalDateTime.of(2025, 1, 1, 11, 55, 0)
    val thresholdMinutes = 2
    val reading = SensorReading("sensor_1", 25.1, timestampSensor)

    assert(!reading.isRecent(timestampNow, thresholdMinutes))
  }

  test("test group readings") {
    val inputReadings: List[SensorReading] = List(
      SensorReading("sensor_1", -1.0, timestamp),
      SensorReading("sensor_2", 23.1, timestamp),
      SensorReading("sensor_2", 26.0, timestamp),
      SensorReading("sensor_1", 45.4, timestamp),
      SensorReading("sensor_3", 0.0, timestamp)
    )

    val expectedOutput = Map(
      "sensor_1" -> List(SensorReading("sensor_1", -1.0, timestamp), SensorReading("sensor_1", 45.4, timestamp)),
      "sensor_2" -> List(SensorReading("sensor_2", 23.1, timestamp), SensorReading("sensor_2", 26.0, timestamp)),
      "sensor_3" -> List(SensorReading("sensor_3", 0.0, timestamp))
    )
    val actualOutput = SensorProcessor.groupReadings_v2(inputReadings)

    assert(actualOutput == expectedOutput)
  }

  test("test avg per sensor") {
    val input = Map(
      "sensor_1" -> List(SensorReading("sensor_1", -1.0, timestamp), SensorReading("sensor_1", 1.0, timestamp)),
      "sensor_2" -> List(SensorReading("sensor_2", 22.0, timestamp), SensorReading("sensor_2", 26.0, timestamp)),
      "sensor_3" -> List(SensorReading("sensor_3", 1.2, timestamp))
    )

    val expectedOutput = Map(
      "sensor_1" -> 0.0,
      "sensor_2" -> 24.0,
      "sensor_3" -> 1.2
    )

    val actualOutput = SensorProcessor.avgReadingPerSensor(input)
    assert(actualOutput == expectedOutput)
  }
}
