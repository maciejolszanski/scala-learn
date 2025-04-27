package com.rockthejvm.part2oop

object Generics {

  // generics (type arguments) are used to reuse code on potentially different types
  abstract class IntList {
    // the linked list [1, 2, 3, 4] first is 1, rest is 2, 3, 4
    def first: Int
    def rest: IntList
  }
  class EmptyIntList extends IntList {
    def first: Int = throw new NoSuchElementException
    def rest: IntList = throw new NoSuchElementException
  }
  case class NonEmptyIntList(a: Int, r: IntList) extends IntList {
    def first: Int = a
    def rest: IntList = r
  }

  // reuse this logic on Strings
  // 1 - copy everything
  // 2 - remove the type safety, use Any - defeats the purpose (we'll have no type info on compile type)
  // 3 - type parameters == generics
  abstract class generalList[A] {
    def first: A
    def rest: generalList[A]
    def isEmpty: Boolean
  }

  class EmptyList[A] extends generalList[A] {
    def first: A = throw new NoSuchElementException
    def rest: generalList[A] = throw new NoSuchElementException
    def isEmpty = true
  }

  case class NonEmptyList[A](a: A, r: generalList[A]) extends generalList[A] {
    def first: A = a
    def rest: generalList[A] = r
    def isEmpty = false
  }

  // can add type arguments/generics to traits, abstract classes, case classes, normal classes

  // can add multiple type arguments to classes/traits/...
  trait MyMap[K, V]

  // can add type args for methods
  def second[A](list: generalList[A]) =
    if (list.isEmpty) throw new NoSuchElementException
    else if (list.rest.isEmpty) throw new NoSuchElementException
    else list.rest.first

  def main(args: Array[String]): Unit = {
    val numbers = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, new EmptyList)))
    val firstNumber = numbers.first

    val strings = NonEmptyList("scala", NonEmptyList("rocks", NonEmptyList("!!!", new EmptyList)))
    val firstString = strings.first

    println(second(numbers))
    println(second(strings))
  }
}
