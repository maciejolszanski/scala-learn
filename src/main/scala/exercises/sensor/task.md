markdown_content = """
# Scala Exercises – SensorReading and SensorProcessor

## Stage 1 – Reinforcing the Basics

**Task 1: Add a timestamp**  
Extend the `SensorReading` class to also include a field `timestamp: Long` (or `LocalDateTime` from `java.time`). Override `toString` to print a human‑readable reading time.  
Goal: using `java.time`, working with date/time, overriding `toString`.

**Task 2: Method `isRecent(thresholdMinutes: Long): Boolean`**  
Add a method that checks if the reading was taken within the last X minutes relative to “now.”  
Goal: time operations, comparisons.

---

## Stage 2 – Collection Processing

**Task 3: Group by ID**  
In the `SensorProcessor` object, add a method that groups a list of readings by `id` and returns `Map[String, List[SensorReading]]`.  
Goal: `groupBy`.

**Task 4: Average temperature per sensor**  
For the grouped data (`Map[String, List[SensorReading]]`), compute the average temperature for each sensor.  
Goal: `mapValues`, `foldLeft`.

**Task 5: Failure detection**  
Design a method that detects if a sensor has been reporting a temperature < 5.0 for the last 10 minutes, which could indicate a malfunction.  
Goal: filtering by time and value, creating alerts.

---

## Stage 3 – Analytics and Functional Challenges

**Task 6: Temperature trend**  
For each sensor’s readings, create pairs `(currentReading, previousReading)` and determine whether the temperature is rising or falling.  
Goal: `sliding`, `zip`.

**Task 7: Outliers**  
Implement a method that detects temperature anomalies—for example, if a value deviates by more than 2 degrees from the sensor’s average.  
Goal: statistics + filtering based on aggregation.

---

## Stage 4 – Mini‑Project

**Task 8: Sensor Summary Report**  
Create a method that produces an hourly summary for each hour:
- number of readings,
- average temperature,
- min/max temperature,
- how many sensors reported,
- how many of them had a high temperature.

Proposed data structure:
```scala
case class SensorSummary(
  hour: String,
  count: Int,
  avg: Double,
  min: Double,
  max: Double,
  highCount: Int
)
