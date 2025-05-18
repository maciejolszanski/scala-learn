package exercises.sensor

case class TemperatureTrend(
                           previous: SensorReading,
                           current: SensorReading,
                           trend: String
                           ) {
}
