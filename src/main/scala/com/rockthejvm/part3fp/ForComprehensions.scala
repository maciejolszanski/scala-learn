package com.rockthejvm.part3fp

object ForComprehensions {

  //example
  val numbers = List(1,2,3,4)
  val colors = List("black", "white", "blue")

  // ["1-black","1-white","1-blue","2-black", ...]
  val combinations = numbers.flatMap(n => colors.map(c => s"$n-$c"))

  // it can be also done using for comprehensions
  val combinations_v2 = for {
    n <- numbers // generator
    c <- colors // generator
  } yield s"$n-$c" // exactly the same as the above
  // it's not a loop, it's an expression exactly identical with flatMaps and maps!!!
  // for expression with a bunch of generators mean that those collections (generators will be chained with flatMaps, and the yielded thing will be a map

  /*
  Exercises - generate a checkerboard from numbers 1-8 and letters A-H
  */
  val chessboard = for {
    letter <- ('A' to 'H').toList
    number <- (1 to 8).toList
  } yield s"$letter$number"

  def main(args: Array[String]): Unit = {
    println(combinations)
    println(combinations_v2)
    println(chessboard)
  }
}
