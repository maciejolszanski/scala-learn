package com.rockthejvm.part1basics

object StringOperations {

  // string type that are provided by default

  val aString = "Scala Rocks"

  val length = aString.length
  val checkEmpty = aString.isEmpty
  val startsWithScala = aString.startsWith("Scala")
  val combine = aString + "very much"
  val findIndex = aString.indexOf("Rocks") //where the "Rocks" appears for the first time

  // interpolation - inject values or expressions inside a string
  val name = "Alice"
  val age = 12
  val greeting = "Hi, my name is " + name + " and I am " + age + " years old"
  val greeting_v2 = s"Hi, my name is $name and I am $age years old"
  val greeting_v3 = s"Hi, my name is $name and I will be turning ${age + 1} years old"

  def main(args: Array[String]): Unit = {
    println(aString.length)
    println(findIndex)
    println(greeting)
    println(greeting_v2)
    println(greeting_v3)
  }
}
