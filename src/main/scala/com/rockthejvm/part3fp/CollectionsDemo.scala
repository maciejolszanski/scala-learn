package com.rockthejvm.part3fp

object CollectionsDemo {

  // RANGES
  val first10Numbers = 1 to 10
  val first10Numbers_v2 = 1.to(10)

  // ranges are used to repeat an action or convert to a collections
  (1 to 10).map(_ => println("Scala"))
  (1 to 10).foreach(_ => println("Scala")) // it's the same

  val f10Numbers = first10Numbers.toList

  // ARRAYS - native arrays (JVM - stored natively on the OS) - collections of spaces in memory of a certain size
  val anArray = Array.ofDim[Int](1000) // represented as int[] on JVM
  // they are mutable
  anArray.update(31, 42) // sets the value 42 at index 31
  val fifthNumber = anArray.apply(4) // anArray[4]

  // they are mostly used by internal implementations, devs do not use them very often

  // SETS - collections with no duplicates (decided with the equals method), but it guarantees no order
  val aSet = Set(1,2,3,4,1,2,3,5) // [1,2,3,4,5]
  // main functionality is to check if an item is in the set or not
  val aSetContains3 = aSet.contains(3) // true or false
  val aSetContains3_v2 = aSet(3) // the same using apply

  // add or remove items from the set - return a new set
  val aBiggerSet = aSet + 99 // [1,2,3,4,5,99]
  val aSmallerSet = aSet - 1 // [2,3,4,5]

  // concatenation == union
  val anotherSet = Set(4,5,6,7,8,9)
  val muchBiggerSet = aSet.union(anotherSet) // [1,2,3,4,5,6,7,8,9]
  val muchBiggerSet_v2 = aSet ++ anotherSet
  val muchBiggerSet_v3 = aSet | anotherSet

  //difference
  val diffSet = aSet.diff(anotherSet) // [1, 2, 3]
  val diffSet_v2 = aSet -- anotherSet

  //intersection
  val intersection = aSet.intersect(anotherSet) // [4,5]
  val intersection_v2 = aSet & anotherSet


  // all of them have map, flatMap and filter => for comprehensions
  val combinations = for {
    x <- Set(1,2,3,4,5,6)
    y <- Set(1,2,3,4,5,6)
  } yield x * y

  // combinations = { x * y | x in {1,2,3,4,5,6}, y in {1,2,3,4,5,6}

  def main(args: Array[String]): Unit = {
    print(combinations)
  }
}
