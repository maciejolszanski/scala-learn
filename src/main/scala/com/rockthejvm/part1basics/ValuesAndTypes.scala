package com.rockthejvm.part1basics

object ValuesAndTypes {

  // val is a constant, you cannot reassign value
  val meaningOfLife: Int = 42

  // type inference
  // you don't have to always declare the type
  val anInteger = 67

  // common types
  val aBoolean: Boolean = false // or true
  val aChar: Char = 'a'
  val anInt: Int = 45 // 4 bytes
  val aShort: Short = 5243 // 2 bytes
  val aLong: Long = 5431565432134L // 8-byte integers
  val aFloat: Float = 2.4f // 4-byte decimal
  val aDouble: Double = 3.14 // 8-byte decimal

  // string type
  val aString: String = "Scala Rocks"


  def main(args: Array[String]): Unit = {

  }

}
