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

  def main(args: Array[String]): Unit = {
    println(daniel.allCaps)
    println(daniel.greet("Alice"))
    println(daniel.greet())
  }
}
