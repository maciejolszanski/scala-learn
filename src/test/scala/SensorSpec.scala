import exercises.sensor.{SensorProcessor, SensorReading, TemperatureTrend, SensorSummary}
import org.scalatest.funsuite.AnyFunSuite
import exercises.sensor.SensorProcessor.*

import java.time.LocalDateTime

class SensorSpec extends AnyFunSuite {
  val timestamp: LocalDateTime = LocalDateTime.of(2025, 1, 1, 12, 0, 0)
  val inputReadings1: List[SensorReading] = List(
    SensorReading("sensor", -1.0, timestamp),
    SensorReading("sensor", 23.1, timestamp),
    SensorReading("sensor", 26.0, timestamp),
    SensorReading("sensor", 45.4, timestamp),
    SensorReading("sensor", 0.0, timestamp)
  )

  test("filtering") {
    val actualResult = inputReadings1.filterReadings(24.0)
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
    val actualResult = inputReadings2.avgReadings
    val expectedResult = 1.0
    assert(actualResult == expectedResult)
  }

  test("average v2") {
    val actualResult = inputReadings2.avgReadings_v2
    val expectedResult = 1.0
    assert(actualResult == expectedResult)
  }

  test("average empty list") {
    val actualResult = List().avgReadings
    val expectedResult = 0.0
    assert(actualResult == expectedResult)
  }

  test("average empty list v2") {
    val actualResult = List().avgReadings_v2
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
    val actualOutput = inputReadings.groupReadings_v2

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

    val actualOutput = input.avgReadingPerSensor
    assert(actualOutput == expectedOutput)
  }

  test("is reading alarming") {
    val timestampNow = LocalDateTime.of(2025, 1, 1, 12, 30, 0)
    val timestampRecent = LocalDateTime.of(2025, 1, 1, 12, 25, 0)
    val thresholdMinutes = 10
    val thresholdTemperature = 5

    val reading = SensorReading("sensor", 2.0, timestampRecent)

    val actualOutput = reading.isReadingAlarming(timestampNow, thresholdMinutes, thresholdTemperature)
    assert(actualOutput)
  }
  test("is reading not alarming") {
    val timestampNow = LocalDateTime.of(2025, 1, 1, 12, 30, 0)
    val timestampRecent = LocalDateTime.of(2025, 1, 1, 12, 25, 0)
    val thresholdMinutes = 10
    val thresholdTemperature = 5

    val reading = SensorReading("sensor", 5.0, timestampRecent)

    val actualOutput = reading.isReadingAlarming(timestampNow, thresholdMinutes, thresholdTemperature)
    assert(!actualOutput)
  }

  test("test alerts for sensors") {
    val timestampNow = LocalDateTime.of(2025, 1, 1, 12, 30, 0)
    val timestampRecent = LocalDateTime.of(2025, 1, 1, 12, 25, 0)
    val timestampOld = LocalDateTime.of(2025, 1, 1, 11, 20, 0)
    val thresholdMinutes = 10
    val thresholdTemperature = 5

    val input = Map(
      "sensor_1" -> List(SensorReading("sensor_1", -1.0, timestampRecent), SensorReading("sensor_1", 22.0, timestampRecent)),
      "sensor_2" -> List(SensorReading("sensor_2", 0.0, timestampOld), SensorReading("sensor_2", 26.0, timestampOld)),
      "sensor_3" -> List(SensorReading("sensor_3", 1.2, timestampRecent), SensorReading("sensor_3", 1.3, timestampRecent))
    )

    val expectedOutput = Map(
      "sensor_1" -> List(SensorReading("sensor_1", -1.0, timestampRecent)),
      "sensor_3" -> List(SensorReading("sensor_3", 1.2, timestampRecent), SensorReading("sensor_3", 1.3, timestampRecent))
    )

    val actualOutput = input.getAlarmingReadings(thresholdMinutes, thresholdTemperature, timestampNow)

    assert(actualOutput == expectedOutput)
  }

  test("test alerts for sensors v2") {
    val timestampNow = LocalDateTime.of(2025, 1, 1, 12, 30, 0)
    val timestampRecent = LocalDateTime.of(2025, 1, 1, 12, 25, 0)
    val timestampOld = LocalDateTime.of(2025, 1, 1, 11, 20, 0)
    val thresholdMinutes = 10
    val thresholdTemperature = 5

    val input = Map(
      "sensor_1" -> List(SensorReading("sensor_1", -1.0, timestampRecent), SensorReading("sensor_1", 22.0, timestampRecent)),
      "sensor_2" -> List(SensorReading("sensor_2", 0.0, timestampOld), SensorReading("sensor_2", 26.0, timestampOld)),
      "sensor_3" -> List(SensorReading("sensor_3", 1.2, timestampRecent), SensorReading("sensor_3", 1.3, timestampRecent))
    )

    val expectedOutput = Map(
      "sensor_1" -> List(SensorReading("sensor_1", -1.0, timestampRecent)),
      "sensor_3" -> List(SensorReading("sensor_3", 1.2, timestampRecent), SensorReading("sensor_3", 1.3, timestampRecent))
    )

    val actualOutput = input.getAlarmingReadings_v2(thresholdMinutes, thresholdTemperature, timestampNow)

    assert(actualOutput == expectedOutput)
  }

  test("determine temperature trends"){
    val input = List(
      SensorReading("sensor_1", 22.0, LocalDateTime.of(2025, 1, 1, 12, 0, 0)),
      SensorReading("sensor_1", 21.0, LocalDateTime.of(2025, 1, 1, 12, 5, 5)),
      SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 1, 0)),
      SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 2, 0))
    )
    val expectedOutput = List(
      TemperatureTrend(
        SensorReading("sensor_1", 22.0, LocalDateTime.of(2025, 1, 1, 12, 0, 0)),
        SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 1, 0)),
        "rising"
      ),
      TemperatureTrend(
        SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 1, 0)),
        SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 2, 0)),
        "steady"
      ),
      TemperatureTrend(
        SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 2, 0)),
        SensorReading("sensor_1", 21.0, LocalDateTime.of(2025, 1, 1, 12, 5, 5)),
        "falling"
      )
    )

    val actualOutput = input.determineTemperatureTrend
    assert(actualOutput == expectedOutput)
  }

  test("test outliers"){
    val input = List(
      SensorReading("sensor_1", 22.0, LocalDateTime.of(2025, 1, 1, 12, 0, 0)),
      SensorReading("sensor_1", 21.0, LocalDateTime.of(2025, 1, 1, 12, 5, 5)),
      SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 1, 0)),
      SensorReading("sensor_1", 35.0, LocalDateTime.of(2025, 1, 1, 12, 2, 0))
    )
    val expectedOutput = List(
      SensorReading("sensor_1", 35.0, LocalDateTime.of(2025, 1, 1, 12, 2, 0))
    )

    val actualOutput = input.getOutliers(5)
    assert(actualOutput == expectedOutput)
  }

  test ("test get hour string"){
    val actualOutput = SensorReading("sensor_1", 22.0, LocalDateTime.of(2025, 1, 1, 12, 0, 0)).createHourString
    val expectedOutput = "2025-01-01 12:00-12:59"

    assert(actualOutput == expectedOutput)
  }

  test("test Sensor summary") {
    val input = List(
      SensorReading("sensor_1", 22.0, LocalDateTime.of(2025, 1, 1, 12, 0, 0)),
      SensorReading("sensor_1", 21.0, LocalDateTime.of(2025, 1, 1, 12, 1, 5)),
      SensorReading("sensor_1", 23.0, LocalDateTime.of(2025, 1, 1, 12, 2, 0)),
      SensorReading("sensor_1", 225.0, LocalDateTime.of(2025, 1, 1, 12, 3, 0)),
      SensorReading("sensor_1", 25.0, LocalDateTime.of(2025, 1, 1, 13, 0, 0)),
      SensorReading("sensor_1", 26.0, LocalDateTime.of(2025, 1, 1, 13, 1, 5)),
      SensorReading("sensor_1", 27.0, LocalDateTime.of(2025, 1, 1, 13, 2, 0)),
    )

    val expectedOutput = Map(
      "2025-01-01 12:00-12:59" -> SensorSummary("2025-01-01 12:00-12:59", 4, 72.75, 21.0, 225.0, 1),
      "2025-01-01 13:00-13:59" -> SensorSummary("2025-01-01 13:00-13:59", 3, 26.0, 25.0, 27.0, 2)
    )

    val actualOutput = input.summarize(25)

    assert(actualOutput == expectedOutput)
  }
}