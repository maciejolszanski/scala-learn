package com.rockthejvm.part3fp

object AnonymousFunctions {

  val doubler: Function1[Int, Int] = new Function1[Int, Int] {
    override def apply(n: Int) = n * 2
  }

  // function types
  val doubler_v2: Int => Int = (n: Int) => n * 2 // lambda, equivalent to the above

  // more complex function types
  val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int) = a + b
  }
  val adder_v2: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // zero arg functions Function0[A]
  val justDoSomething: () => Int = () => 42

  // alt syntax with curly braces
  val stringToInt = { (string: String) =>
    // block of code
    val stringLength = string.length
    stringLength + 45
  }

  // type inference
  val doubler_v3: Int => Int = n => n * 2
  val adder_v3: (Int, Int) => Int = (a, b) => a + b

  // shortest notation possible
  val doubler_v4: Int => Int = _ * 2 // same as n => n * 2
  val adder_v4: (Int, Int) => Int = _ + _ // each underscore is a different argument

  def main(args: Array[String]): Unit = {
    println(doubler(2))
    println(doubler_v2(2))
    println(adder(2, 3))
    println(adder_v2(2, 3))

    println(doubler_v3(2))
    println(doubler_v4(2))

    println(adder_v3(2, 3))
    println(adder_v4(2, 3))
  }
}
