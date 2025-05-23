package exercises.sensor

case class SensorSummary(
  sensor_id: String,
  hour: String,
  count: Int,
  avg: Double,
  min: Double,
  max: Double,
  highCount: Int
)
