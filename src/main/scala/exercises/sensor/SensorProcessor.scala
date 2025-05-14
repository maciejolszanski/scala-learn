package exercises.sensor

object SensorProcessor {
  def filterReadings(readings: List[SensorReading], minTemperature: Double): List[SensorReading] = {
    readings.filter(x => x.isHighTemp(minTemperature))
  }

  def main(args: Array[String]): Unit = {
    val inputReadings: List[SensorReading] = List(
      SensorReading(1, -1.0),
      SensorReading(2, 23.0),
      SensorReading(3, 26.5),
      SensorReading(4, 45.4),
      SensorReading(5, 0.0)
    )
    println(filterReadings(inputReadings, 24.0))
  }
}
