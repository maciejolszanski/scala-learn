package com.rockthejvm.part2oop

object MethodNotation {

  class Person(val name: String) {

    def greet(another: Person): String =
      s"$name says: hi, ${another.name}, how are you?"

    // infix works with or without this infix notation, but it's the best practice
    infix def likes(movie: String): String =
      s"$name says: I adore the movie $movie!!!"

    // it's also valid method name
    infix def ?!(another: Person): String =
      s"$name says: Hey ${another.name}, how could you do this?"

    // apply is a special method!
    def apply(progLang: String): Unit =
      println(s"[$name] Programming in $progLang")

    def apply(progLang: String, algorithm: String): Unit =
      println(s"[$name] Programming in $progLang, applying the algorithm $algorithm")
  }

  val alice = Person("Alice")
  val bob = Person("Bob")

  def main(args: Array[String]): Unit = {
    println(alice.greet(bob))
    println(alice    greet     bob) // exactly the same thing - infix notation
    //     ^^^^^^    ^^^^^     ^^^
    //     instance  method  argument
    // infix notation ONLY works on methods with ONE argument

    println(alice.likes("Forrest Gump"))
    println(alice likes "Forrest Gump")

    println(alice.?!(bob))
    println(alice ?! bob)

    alice.apply("Scala")
    alice("Scala") // it's exactly the same as alice.apply("Scala")

    alice("Scala", "Dynamic Programming")
  }
}
