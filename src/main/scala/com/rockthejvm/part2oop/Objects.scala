package com.rockthejvm.part2oop

object Objects {

  // singleton pattern ensures you define a type with only one possible instance of that type
  // in scala it's defined by object keyword
  object MySingleton { //MySingleton type AND the ONLY possible instance of that type
    // can define fields and methods
    val aField = 45
    def aMethod(x: Int) = x + 1
  }
  val theSingleton = MySingleton
  val anotherSingleton = MySingleton

  // combination of class + object of the same name in the same file = companions
  class Person(name: String) {
    def sayHi: String = s"Hi, my name is $name"
  }
  object Person {
    // object with the same name = companion object
    // we do it to define "static" fields and methods - that do not depend on a particular instance of the class
    val N_EYES = 2
    def canFly(): Boolean = false
  }

  // objects can extend classes/traits
  object BigFoot extends Person("Big Foot")

  val bigFoot = BigFoot
  /*
  Scala compiles to JVM bytecode
  JVM understands a class with a static void main(args: Array["String])

  in Java:
    class MyApplication {
      public static void main(String[] args) { }
    }

  in Scala:
    object MyApplication {
      def main(args: Array[String]): Unit = {}
    }
  */

  def main(args: Array[String]): Unit = {
    println(theSingleton == anotherSingleton)
    println(MySingleton.aField)
    println(MySingleton.aMethod(5))

    println(bigFoot.sayHi)
  }
}
