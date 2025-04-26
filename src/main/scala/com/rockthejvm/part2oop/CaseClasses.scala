package com.rockthejvm.part2oop

object CaseClasses {

  //lightweight data structures
  case class Pet(name: String, age: Int) {
    //define fields and methods
  }

  // case class have a bunch of extra properties by default
  // 1 - calls constructor arguments are automatically promoted to fields
  val dino = new Pet("Dino", 2)
  val dinoAge = dino.age

  // 2 - case classes can be built without the 'new' keyword (in Scala 3 all classes can)
  val dino_v2 = Pet("Dino", 2)

  // 3 - already some representations setup by compiler: toString, equals, hashCode (Any type)
  class PetSimple(name: String, age: Int) // toString will not be human-readable, for Case Classes it will
  val dinoSimple = new PetSimple("Dino", 2)
  println(dino == dino_v2) // will be true because of Case Classes

  // 4 - utility methods e.g. copy
  val dinoYounger = dino.copy(age = 1) // new instance of Pet with the same data, EXCEPT the argument I pass

  // 5 - Companion objects already created
  val dino_v3 = Pet.apply("Dino", 2)
  val dino_v4 = Pet("Dino", 2) // that's the same what apply(), and that's why we do not need new keyword

  // 6 - they are serializable - instance of the class can be sent across the wire or stored in a file so it can be used later
  // it's super useful for parallel/distributed systems

  // 7 - eligible for pattern matching

  // case objects - only instances of their own types
  case object UnitedKingdom {
    def name: String = "the uk of gb and ni"
  }
  // we create case objects to define hierarchies of data structures, some are their won types
  trait Message
  case class InitialInteraction(message: String) extends Message
  //other case classes
  case object EndConversation extends Message

  //pattern matching

  //some external method
  def getMessage(): Message =
    InitialInteraction("Hello")

  val message: Message = getMessage()
  val contents = message match {
    case InitialInteraction(someContent) => s"I've received: $someContent"
    case EndConversation => "end of chat"
  }

  def main(args: Array[String]): Unit = {
    println(dinoSimple.toString)  // not human-readable
    println(dino.toString) // human-readable
    println(dinoYounger)
    println(contents)
  }
}
