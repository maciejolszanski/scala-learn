package exercises.sensor

case class SensorSummary(
  hour: String,
  count: Int,
  avg: Double,
  min: Double,
  max: Double,
  highCount: Int
)
