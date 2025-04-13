package com.rockthejvm.part1basics

object Instructions {

  // instructions vs expressions
  // instructions are executed - expressions are evaluated
  // do this, do that, repeat(10) {...}  - imperative programming
  // 2 + 3, IO effects, map, flatMap, filter - functional programming

  // everything in Scala is an expression - so how do we model an instruction?
  // instruction is a structure (expression) returning Unit
  val printing: Unit = println("this is an instruction") // in the process of evaluating it we have a side effect that prints it out

  val theUnit: Unit = () // the only possible value of type Unit

  // instructions = code blocks returning Unit
  val aCodeBlock = {
    val aLocalValue = 45
    println("ins 1")
    println("ins 2")
  }

  // variables in Scala
  var aVariable = 10
  val reassignment: Unit = aVariable += 1

  // loops in scala

  def main(args: Array[String]): Unit = {
    println(printing == theUnit)

    // loops in scala
    var theNumber = 1
    while (theNumber <= 10) {
      println(theNumber)
      theNumber += 1
    }
  }
}
