package com.rockthejvm.part3fp

object ListsExercises {

  /*
  1. [1, 2, 3] => ["Scala", "ScalaScala", "ScalaScalaScala"]
  2. Generate the first 100 numbers in a list with a recursive function.
  3. From the list from ex. 2. return just the odd numbers
  4. Take a list of numbers, return a list of all the digits of those numbers
      [13, 249] => [1,3,2,4,9]
  */

  // 1
  val aList: List[Int] = List(1, 2, 5)
  val aScalaList: List[String] = aList.map(x => "Scala"*x)

  // 2
  def genList(n:Int): List[Int] = {
    if (n == 0) then List(0)
    else genList(n - 1) :+ n
  }
  val listTo100: List[Int] = genList(100)

  // 3
  val oddNumbers: List[Int] = listTo100.filter(_ % 2 == 1)

  // 4
  val numbers: List[Int] = List(1, 43, 956)
  val allDigits: List[Int] = numbers.map(_.toString).flatMap(_.toList).map(_.asDigit)

  // 4 - another approach
  def digits(n: Int): List[Int] = {
    def getDigits(number:Int): List[Int] = {
      if (number <= 0) List()
      else {
        val lastDigit = number % 10
        val quotient = number / 10
        lastDigit :: getDigits(quotient)
      }
    }
    if (n < 0) List()
    else if (n == 0) List(0)
    else getDigits(n).reverse
  }
  def listofDigits(numbers: List[Int]): List[Int] ={
    numbers.flatMap(n => digits(n))
  }

  def main(args: Array[String]): Unit = {
    println(aScalaList)
    println(listTo100)
    println(oddNumbers)
    println(allDigits)
    println(listofDigits(List(1, 23, 768)))
  }
}
