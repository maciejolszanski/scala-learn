package com.rockthejvm.part2oop

object AbstractDataTypes {

  // Abstract classes
  abstract class VideoGame {
    // fields or methods without an implementation = abstract
    val gameType: String
    def play(): Unit

    // can provide "normal" fields or methods
    def runningPlatform: String = "PS5"
  }

  // abstract classes cannot be instantiated
  // val videoGame: VideoGame = new VideoGame
  // derived classes must be either A. abstract or B. with an impl for gameType
  val videoGame: VideoGame = new VideoGame {
    override val gameType: String = "my own genre"
    override def play(): Unit = println("sth")
  }
  class Shooter extends VideoGame {
    override val gameType: String = "FPS"
    override def play(): Unit = println("fire!")
  }

  // Traits
  trait ThirdPerson {
    def showPerspective(game: VideoGame): Unit
  }

  // traits can extend other traits
  trait OpenWorld extends ThirdPerson {
    override def showPerspective(game: VideoGame): Unit = {
      println("I see an open world")
    }
  }
  trait StoryLine {
    def mainCharacter: String
  }

  // abstract classes vs traits
  // a class can extend ONE class, but MULTIPLE traits
  class MassEffect extends VideoGame with OpenWorld with StoryLine {
    override val gameType: String = "open-world-story"
    override def play(): Unit = println("saving the galaxy!")
    override def mainCharacter: String = "Commander Shepard"
  }

  def main(args: Array[String]): Unit = {
    val cs = new Shooter()
    println(cs.runningPlatform)
  }
}
