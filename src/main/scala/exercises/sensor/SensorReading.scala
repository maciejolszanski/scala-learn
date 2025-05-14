package exercises.sensor

case class SensorReading(id: Int, temperature: Double) {
  def isHighTemp(threshold: Double): Boolean = {
    temperature > threshold
  }
}
