package exercises.simplest

object FilteringAggregation {

  //Task: For the list: List(1, 2, 3, 4, 5, 6, 7, 8), return the sum of only the even numbers.
  
  def sumEvenNumbers(numbers: List[Int]): Int = {
    numbers.filter(_ % 2 == 0).sum
  }

  def main(args: Array[String]): Unit = {
    println(sumEvenNumbers(List(1)))
  }
}
