package com.rockthejvm.part2oop

object Exceptions {

  def sumN(n: Int): Int = {
    if (n < 0) throw new IllegalArgumentException("Negative numbers unsupported")
    else if (n >= 50000) throw new RuntimeException("Too big of a number - overflowing Int!")
    else if (n <= 0) 0
    else n + sumN(n-1)
  }

  // try-catch
  val attempt = try {
    //block of code that can crash
    println(sumN(-10))
  } catch {
    case e: IllegalArgumentException => -1
    case e: RuntimeException => Int.MaxValue
  }

  val attempt_v2 =
    try
      println(sumN(-10))
    catch
      case e: IllegalArgumentException => -1
      case e: RuntimeException => Int.MaxValue

  /*
      Hierarchy of Exceptions
      Throwable
      - Error - JVM related crashes (out of memory, stack overflow)
      - Exception - user-facing crashes
        - Runtime Exception
        ^^ can define new exceptions
   */

  class MyException extends RuntimeException("Too big of a number - overflowing Int!")

  def sumN_v3(n: Int): Int = {
    if (n < 0) throw new IllegalArgumentException("Negative numbers unsupported")
    else if (n >= 50000) throw new MyException
    else if (n <= 0) 0
    else n + sumN(n - 1)
  }

  val attempt_v3 = try {
    //block of code that can crash
    println(sumN_v3(1000000))
  } catch {
    case e: IllegalArgumentException => -1
    case e: MyException => Int.MaxValue
  }

  def main(args: Array[String]): Unit = {
    println(attempt)
    println(attempt_v2)
    println(attempt_v3)
  }
}
