package com.rockthejvm.part3fp

object TuplesMapsDemo {

  // tuples
  val aTuple: (Int, String) = (2, "rock the jvm")
  val firstField = aTuple._1
  val secondField = aTuple._2

  // tuples of 2 elements have some syntax sugar
  val aTuple_v2 = 2 -> "rock the jvm" // same as a aTuple

  // maps - associate keys to values, unique keys
  val aMap = Map()
  val phonebook = Map(
    ("Jim", 555),
    "Daniel" -> 919,
    "Jane" -> 123
  )

  // core maps methods
  // key exists in a map
  val hasDaniel = phonebook.contains("Daniel") //true
  val danielsNumber = phonebook("Daniel") //apply - returns 919 will crash if the key is not in the map

  // add a new pair - returns a new map
  val newPair = "Mary" -> 678
  val newPhonebook = phonebook + newPair // new map

  // remove a key
  val noDaniel = phonebook - "Daniel" // new map

  // list of tuples <-> maps
  val listOfEntries = List(
    "Jim" -> 555,
    "Daniel" -> 919,
    "Jane" -> 123
  )

  val phonebook_v2 = listOfEntries.toMap
  val listOfEntries_v2 = phonebook.toList

  // set of keys
  val allNames = phonebook.keySet
  val allNumbers = phonebook.values.toList // [555,919,123] not necessarily in this order

  def main(args: Array[String]): Unit = {

  }
}
