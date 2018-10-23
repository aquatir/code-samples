package codesample.scala.coursera.course1.week2

import scala.annotation.tailrec

object HighOrderFuncsAndCurrying {

  def sum(intFunc: Int => Int, left: Int, right: Int) : Int = {
    if (left > right)
      0
    else
      intFunc(left) + sum(intFunc, left + 1, right)
  }

  def sumTail(f: Int => Int, left: Int, right: Int): Int = {
    @tailrec
    def loop(left: Int, acc: Int): Int = {
      if (left > right) acc
      else loop(left + 1, acc + f(left))
    }

    loop(left, 0)
  }

  def sumCurrying(intFunc: Int => Int) : (Int, Int) => Int = {
    def sumCurryingF(left: Int, right: Int): Int = {
      if (left > right)
        0
      else
        intFunc(left) + sumCurryingF(left + 1, right)
    }

    sumCurryingF
  }

  def sumScalaCurrying(intFunc: Int => Int)(left: Int, right: Int): Int = {
    if (left > right)
      0
    else
      intFunc(left) + sumScalaCurrying(intFunc)(left + 1, right)
  }



  def main(args: Array[String]): Unit = {
    def id (element: Int) = element
    def square(element: Int) = element * element

    println(s"sum from 0 to 10 is: ${sum(id, 0, 10)}")
    println(s"sum of squares from 0 to 10 is ${sum(square, 0, 10)}")
    println(s"sum of cubes from 0 to 10 is ${sum(x => x * x * x, 0, 10)}")

    println(s"tailrec: sum from 0 to 10 is: ${sumTail(x => x, 0, 10)}")
    println(s"tailrec: sum of squares from 0 to 10 is ${sumTail(x => x * x, 0, 10)}")
    println(s"tailrec: sum of cubes from 0 to 10 is ${sumTail(x => x * x * x, 0, 10)}")

    println(s"sumCurrying: sum from 0 to 10 is: ${sumCurrying(id) (0, 10)}")
    println(s"sumCurrying: sum of squares from 0 to 10 is ${sumCurrying(square) (0, 10)}")
    println(s"sumCurrying: sum of cubes from 0 to 10 is ${sumCurrying(x => x * x * x) (0, 10)}")

    println(s"sumScalaCurrying: sum from 0 to 10 is: ${sumScalaCurrying(id) (0, 10)}")
    println(s"sumScalaCurrying: sum of squares from 0 to 10 is ${sumScalaCurrying(square) (0, 10)}")
    println(s"sumScalaCurrying: sum of cubes from 0 to 10 is ${sumScalaCurrying(x => x * x * x) (0, 10)}")
  }


}
