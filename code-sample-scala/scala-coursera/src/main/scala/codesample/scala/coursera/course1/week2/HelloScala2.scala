package codesample.scala.coursera.course1.week2

object HelloScala2 {

  def sum(intFunc: Int => Int, left: Int, right: Int) : Int = {
    if (left > right)
      0
    else
      intFunc(left) + sum(intFunc, left + 1, right)
  }



  def main(args: Array[String]): Unit = {
    def id (element: Int) = element
    def square(element: Int) = element * element

    println(s"sum from 0 to 10 is: ${sum(id, 0, 10)}")
    println(s"sum of squares from 0 to 10 is ${sum(square, 0, 10)}")
  }


}
