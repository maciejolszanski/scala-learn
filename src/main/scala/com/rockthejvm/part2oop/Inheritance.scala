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

  // overriding = provide a new impl for fields or methods in derived classes
  class RPG extends VideoGame {
    override val gameType = "role-playing"

    override def play(): Unit = { // super relates to the parent class
      println("level up!")
    }
  }
  val wow = new RPG()

  // subtype polymorphism
  val wow_2: VideoGame = new RPG // declare a value of a parent type, provide and instance of a derived type

  // anonymous classes
  val psychonauts = new VideoGame {
    override val gameType = "platformer"
  }
  // that's something like this:
  /*
    class AnonClass$3274 extends VideoGame {
      override val gameType = "platformer"
    }

    val psychonauts: VideoGame = new AnonClass$3274
  */

  def main(args: Array[String]): Unit = {
    println(crysis.gameType)
    crysis.multiplayer

    println(daniel.name)
    println(maciek.name)

    println(wow.gameType)
    wow.play()
    println(wow_2.gameType)
    wow_2.play()
  }
}
