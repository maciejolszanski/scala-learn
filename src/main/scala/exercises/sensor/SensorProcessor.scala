package exercises.sensor

object SensorProcessor {
  def filterReadings(readings: List[SensorReading], minTemperature: Double): List[SensorReading] = {
    readings.filter(x => x.isHighTemp(minTemperature))
  }

//  def foldLeft[B](z: B)(op: (B, A) => B): B
  def avgReadings(readings: List[SensorReading]): Double = {
    val (sum, count) = readings.foldLeft(0.0, 0){ (accumulator, reading) =>
      val accSum = accumulator._1
      val accCount = accumulator._2
      (accSum + reading.temperature, accCount + 1)
    }

    if count > 0 then sum / count
    else 0.0
  }

  def avgReadings_v2(readings: List[SensorReading]): Double = {
    val (sum, count) = readings.foldLeft(0.0, 0) {
      case ((accSum, accCount), reading) => (accSum + reading.temperature, accCount + 1)
    }

    if count > 0 then sum / count
    else 0.0
  }

  def groupReadings(readings: List[SensorReading]): Map[String, List[SensorReading]] = {
    readings.groupBy(_.sensor_id)
  }
  def groupReadings_v2(readings: List[SensorReading]): Map[String, List[SensorReading]] = {
    readings.foldLeft(Map.empty[String, List[SensorReading]]) { (acc, reading) =>
      val currList = acc.getOrElse(reading.sensor_id, List())
      acc.updated(reading.sensor_id, currList :+ reading)
    }
  }
  
  def avgReadingPerSensor(grouped_readings: Map[String, List[SensorReading]]): Map[String, Double] = {
    grouped_readings.map((id, readings) => (id, avgReadings(readings)))
  }
}