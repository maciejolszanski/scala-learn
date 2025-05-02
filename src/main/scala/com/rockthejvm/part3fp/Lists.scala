package com.rockthejvm.part3fp

object Lists {

  val aList: List[Int] = List.apply(1, 2, 3)
  val aList_v2: List[Int] = List(1, 2, 3)

  // list in scala is a singly linked list
  // first element (head), rest of the list (tail)
  val firstElement: Int = aList.head
  val aRest: List[Int] = aList.tail

  // add (prepend) an element to the list
  val biggerList = 0 :: aList // [0, 1, 2, 3] - original list is left intact

  // add (append) an element to the list
  val biggerList_v2 = aList :+ 4 // [1, 2, 3, 4]

  // Explanation of the below notation
  def add2numbers(x: Int)(y: Int): Int = x + y

  // utility methods
  val scalax5 = List.fill(5)("Scala")

  // reverse a List
  val reversedList = aList.reverse // new list [3, 2, 1]

  // concatenate with another list
  val concatenantion = aList ++ List(4, 5, 6)

  // format the collection
  val strinRep = aList.mkString(", ") // "1, 2, 3"
  val strinRep_v2 = aList.mkString("|") // "1|2|3"
  val strinRep_v3 = aList.mkString("[", ",", "]") // "[1,2,3]"

  // combine all elements in a list
  val total = aList.reduce((a, b) => a + b)
  val total_v2 = aList.reduce(_ + _)

  // map - transforms a list by applying a function to every element
  /* in imperative language it'd be:

    val anArray = [1, 2, 3]
    va result = Array[3]
    for (int i=0, i < anArray.size; i++)
      result[i] = anArray[i * 3]
    return result
  */
  val tenxList = aList.map(x => x * 10) // [10,20,30]
  val scalaList = aList.map(x => s"Scala $x")

  // filter - transform a list by just keeping the items satisfying a boolean function
  val evenNumbers = aList.filter(x => x % 2 == 0) // [2] - a new list, the original one is kept intact

  // higher-order functions = take other functions as arguments/return functions as results

  // flatMap - transform a List by applying the functions to every element  + combining the mini-list obtained into one big list
  val expandedList = aList.map(x => List(x, x+1)) // [List(1, 2), List(2, 3), List(3, 4)]
  val expandedList_v2 = aList.flatMap(x => List(x, x+1)) // [1, 2, 2, 3, 3, 4]


  def main(args: Array[String]): Unit = {
    println(aList.toString)
    println(aList) // the same

    println(biggerList)
    println(biggerList_v2)

    println(strinRep)
    println(strinRep_v2)
    println(strinRep_v3)

    println(total)
    println(total_v2)

    println(tenxList)
    println(scalaList)

    println(evenNumbers)
  }
}
