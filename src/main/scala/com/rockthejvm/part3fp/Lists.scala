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
  }
}
