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
}
