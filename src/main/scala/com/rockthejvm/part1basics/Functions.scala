package com.rockthejvm.part1basics

object Functions {

  def aFunction(a: String, b: Int): String =
    // always a single instruction (code block is also a single instruction)
    a + ' ' + b

  // invocation
  val anInvocation = aFunction("scala", 999)

  // functions returning Unit
  // Unit == Void in other languages
  def aVoidFunction(aString: String): Unit =
    println(aString)

  def functionWithSideEffects(aString: String): String = {
    println(aString)
    aString + ' ' + aString
  }

  //can define smaller functions inside bigger ones
  def aBigFunctions(n: Int): Int = {
    // can define a smaller function
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    //smaller function is not accessible outside of this code block
    aSmallerFunction(n, n+1)
  }


  def main(args: Array[String]): Unit = {
    println(anInvocation)
    println(functionWithSideEffects("text"))
  }
}
