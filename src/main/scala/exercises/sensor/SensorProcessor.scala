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


  def main(args: Array[String]): Unit = {
    val inputReadings: List[SensorReading] = List(
      SensorReading(1, -1.0),
      SensorReading(2, 23.1),
      SensorReading(3, 26.0),
      SensorReading(4, 45.4),
      SensorReading(5, 0.0)
    )

    println(avgReadings_v2(inputReadings))
  }
}

//Zadanie: Oblicz średnią temperatur z listy SensorReading. Nie używaj wbudowanej funkcji sum.
//
//  Cel: foldLeft.