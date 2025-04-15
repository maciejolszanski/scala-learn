package com.rockthejvm.part2oop

object OOBasics {

  class Person(val name: String, age: Int) { // class definition also contains the definition of its CONSTRUCTOR
    // inside, we can define val, var, def
    // fields are val or var
    val allCaps = name.toUpperCase()

    // methods = functions
    def greet(someone: String): String =
      s"$name says: Hi $someone"

    // can define multiple methods with the same name (overloading) if we have different signature
    def greet(): String =
      s"Hi everyone, I'm $name"
  }

  val daniel = new Person("Daniel", 25) // daniel is an instance of Person (instantiation)

  // constructor argument is not necessarily a field (it has to be defined as val/var in a Constructor)
  val danielName = daniel.name

  /*
  * Exercises: image we're doing an app for car registration
  *  - Create a Car and a Driver class

  *   Car: make, model, year of release, owner (Driver)
        - method ownerAge (age of the owner at the year of release)
        - method isOwnedBy(driver: Driver) - boolean if the driver is the same as the owner
        - copy (newYearOfRelease) - returns a new Car instance with the same data except the new year of release

  *   Driver: first name, last_name, year of birth
        - method full name
  * */

  class Driver(firstName: String, lastName: String, val yearOfBirth: Int) {
    val fullName: String = firstName + " " + lastName
  }

  class Car(make: String, model: String, yearOfRelease: Int, owner: Driver) {
    val ownerAge: Int = yearOfRelease - owner.yearOfBirth

    def isOwnedBy(driver: Driver): Boolean = {
      driver == owner
    }

    def copy(newYearOfRelease: Int): Car = {
      new Car(make, model, newYearOfRelease, owner)
    }

  }
  def main(args: Array[String]): Unit = {
    println(daniel.allCaps)
    println(daniel.greet("Alice"))
    println(daniel.greet())
    // test the Exercise
    val driver = new Driver("Michael", "Schumacher", 1969)
    val driverImpostor = new Driver("Michael", "Schumacher", 1969)

    val car = new Car("Ferrari", "F1", 2004, driver)

    println(driver.fullName)
    println(car.ownerAge)
    println(car.isOwnedBy(driver))
    println(car.isOwnedBy(driverImpostor))
    println(car.copy(2005).ownerAge)

    println(s"Testing equality: ${driver == driverImpostor}")
  }
}
