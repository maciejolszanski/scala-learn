package exercises.sensor

import java.time.LocalDateTime

object SensorProcessor {

  private def trendForPair(previousReading: SensorReading, currentReading: SensorReading): TemperatureTrend = {
    val trend: String = {
      if currentReading.temperature > previousReading.temperature then "rising"
      else if currentReading.temperature < previousReading.temperature then "falling"
      else "steady"
    }

    TemperatureTrend(previousReading, currentReading, trend)
  }

  extension( readings: List[SensorReading]) {
    def filterReadings(minTemperature: Double): List[SensorReading] = {
      readings.filter(x => x.isHighTemp(minTemperature))
    }

    //  def foldLeft[B](z: B)(op: (B, A) => B): B
    def avgReadings: Double = {
      val (sum, count) = readings.foldLeft(0.0, 0) { (accumulator, reading) =>
        val accSum = accumulator._1
        val accCount = accumulator._2
        (accSum + reading.temperature, accCount + 1)
      }

      if count > 0 then sum / count
      else 0.0
    }

    def avgReadings_v2: Double = {
      val (sum, count) = readings.foldLeft(0.0, 0) {
        case ((accSum, accCount), reading) => (accSum + reading.temperature, accCount + 1)
      }

      if count > 0 then sum / count
      else 0.0
    }

    def groupReadings: Map[String, List[SensorReading]] = {
      readings.groupBy(_.sensor_id)
    }
    def groupReadings_v2: Map[String, List[SensorReading]] = {
      readings.foldLeft(Map.empty[String, List[SensorReading]]) { (acc, reading) =>
        val currList = acc.getOrElse(reading.sensor_id, List())
        acc.updated(reading.sensor_id, currList :+ reading)
      }
    }

    def determineTemperatureTrend: List[TemperatureTrend] = {
      readings
        .sortBy(_.timestamp)
        .sliding(2)
        .collect {
          case List(prev, curr) => trendForPair(prev, curr)
        }
        .toList
    }
  }

  extension (groupedReadings: Map[String, List[SensorReading]]) {
    def avgReadingPerSensor: Map[String, Double] = {
      groupedReadings.map((id, readings) => (id, avgReadings(readings)))
    }

    def getAlarmingReadings(lastMinutesToCheck: Int,
                            minValidTemperature: Double,
                            now: LocalDateTime
                           ): Map[String, List[SensorReading]] = {

      groupedReadings
        .map { case (id, readings) =>
          id -> readings.filter(_.isReadingAlarming(now, lastMinutesToCheck, minValidTemperature))
        }
        .filter { case (_, alarmingReadings) => alarmingReadings.nonEmpty }
    }

    def getAlarmingReadings_v2(lastMinutesToCheck: Int,
                               minValidTemperature: Double,
                               now: LocalDateTime
                              ): Map[String, List[SensorReading]] = {

      val alerts = for {
        (id, readings) <- groupedReadings
        alarming = readings.filter(
          _.isReadingAlarming(now, lastMinutesToCheck, minValidTemperature)
        )
        if alarming.nonEmpty
      } yield id -> alarming

      alerts
    }
  }
}