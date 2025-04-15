package com.rockthejvm.part1basics

object Recursion {

  // in functional programing we try to avoid variables and loops, so recursion replaces them
  def sum(n: Int): Int = {
    if (n <= 0) then 0
    else n + sum(n-1)
  }


  //print string exactly N times
  def printN(string: String, n:Int): Unit = {
    if (n <= 0) () // () - is empty Unit
    else {
      println(string)
      printN(string, n-1)
    }
  }

  //more complex example
  def isPrime(n: Int): Boolean = {
    // we need to test all numer from 2 to n/2 whether n % d == 0
    def testDivisors(d: Int): Boolean = {
      if (d > n/2) true
      else if (n % d == 0) false
      else testDivisors(d + 1)
    }

    // last expression is the final thing
    if (n <= 0) false
    else if (n == 1) false // 1 is not a prime
    else testDivisors(2)
  }

  /*
  * Exercises
    1. concatenate a string a set number of times
      concatenateN("Scala", 3) = "ScalaScalaScala"
    2. Fibonacci Numbers
      1, 2, 3, 5, 8, 13, ...
      fibonacci(3) -> 3
      fibonacci(6) -> 13
  * */

  def concatenateN(string: String, n: Int): String = {
    if n == 0 then ""
    else string + concatenateN(string, n-1)
  }

  def fibonacci(n: Int): Int = {
    if (n <= 1) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }


  def main(args: Array[String]): Unit = {
    println(sum(10))
    printN("Scala is OK", 3)
    println(isPrime(7))
    println(isPrime(8))
    println(concatenateN("Scala", 3)) // "ScalaScalaScala"
    println(fibonacci(6)) // 13
  }
}
