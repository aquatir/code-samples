package codesample.scala.coursera.course1.week4

object ScalaListsPartOne {


  def insSort(xs: List[Int]): List[Int] = xs match {
    case Nil => List()
    case y :: ys => insert(y, insSort(ys))
  }
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case Nil => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  }

  def main(args: Array[String]): Unit = {
    val list = "Keks" :: "Magic" :: "And shit" :: Nil // You can not omit :: Nil
    val otherList = "abc" :: "def" :: Nil
    val mergedList: List[String] = list ::: otherList

    println("list: " + list)
    println("merged list: " + mergedList)

    println("Head of first: " + list.head)
    println("Tail of first: " + list.tail)

    // Isertion sort!
    val someList = List(9,5,3,2,1,6,3,2)
    println("Insertion sorted list: " + someList + " => " + insSort(someList))
  }

}
