package exercises.sensor

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class SensorReading(sensor_id: String, temperature: Double, timestamp: LocalDateTime) {
  def isHighTemp(threshold: Double): Boolean = {
    temperature > threshold
  }

  override def toString: String = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formattedTimestamp = timestamp.format(formatter)
    s"$formattedTimestamp, Sensor: $sensor_id, Temperature: $temperature K"
  }

  def isRecent(now: LocalDateTime, thresholdMinutes: Int): Boolean = {
    now.minusMinutes(thresholdMinutes).isBefore(timestamp)
  }

  def isReadingAlarming(now: LocalDateTime, thresholdMinutes: Int, thresholdTemperature: Double): Boolean = {
    isRecent(now, thresholdMinutes) && temperature < thresholdTemperature
  }

  def createHourString: String = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = timestamp.format(formatter)
    val hour = timestamp.getHour

    s"$date $hour:00-$hour:59"
  }
}