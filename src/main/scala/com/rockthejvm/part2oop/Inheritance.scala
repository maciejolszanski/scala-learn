package com.rockthejvm.part2oop

object Inheritance {

  class VideoGame {
    val gameType = "interactive"
    def play(): Unit = println("game running")
  }

  // single-class inheritance
  class Shooter extends VideoGame {
    //can define my own fields/methods
    def multiplayer = {
      play()
      println("boom, boom!")
    }
  }

  val crysis = new Shooter

  // inherit primary constructor
  class Person(val name: String, age: Int)
  class Adult extends Person("John Doe", 0) // must specify constructor arguments
  class Adult_v2(name: String, age: Int) extends Person(name, age)

  val daniel = new Adult()
  val maciek = new Adult_v2("Maciek", 99)

  def main(args: Array[String]): Unit = {
    println(crysis.gameType)
    crysis.multiplayer

    println(daniel.name)
    println(maciek.name)
  }
}
