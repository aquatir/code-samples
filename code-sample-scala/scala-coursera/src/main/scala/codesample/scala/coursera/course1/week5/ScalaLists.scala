package codesample.scala.coursera.course1.week5

object ScalaLists {


  def insSort(xs: List[Int]): List[Int] = xs match {
    case Nil => List()
    case head :: tail => insert(head, insSort(tail))
  }
  def insert(head: Int, tail: List[Int]): List[Int] = tail match {
    case Nil => List(head)
    case headOfTail :: tailOfTail => if (head <= headOfTail) head :: tail else headOfTail :: insert(head, tailOfTail)
  }

  def bubbleSort(xs: List[Int]): List[Int] = xs match {
    case Nil => List()
    case head :: tail => mayBeSwap(head, bubbleSort(tail));
  }

  def mayBeSwap(head: Int, tail: List[Int]): List[Int] = tail match {
    case Nil => List(head)
    case headOfTail :: tailOfTail =>
      if (head > headOfTail) headOfTail :: mayBeSwap(head, tailOfTail)
      else head :: tail
  }

  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("init of empty list")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }


  def removeAt[T](n: Int, xs: List[T]) = xs.take(n) ::: xs.drop(n+1)

  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case head :: tail => (head match {
      case headValue: List[Any] => flatten(headValue)
      case headValue: Any => List(headValue)
    }) ::: flatten(tail)

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
    val someList = List(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
    println("Insertion sorted list: " + someList + " => " + insSort(someList))
    println("Bubble sorted list: " + someList + " => " + bubbleSort(someList))
    println("All elements but last: " + someList.init)
    println("My init: " + init(someList))

    println("list without first 5 elements: " + someList.drop(5))
    println("only first 5 elements of list: " + someList.take(5))

    println("Reversed list: " + someList.reverse)
    println("Double list" + (someList ++ someList))

    val first = List(1,2,3)
    val second = List(4,5,6)
    println("what ::: do? first ::: second: " + (first ::: second))

    println("element removed: " + removeAt(1, List('a', 'b', 'c', 'd'))) // List(a, c, d)
    println("flattenned: " + flatten(List(List(1, 1), 2, List(3, List(5, 8))))) // 1, 1, 2, 3, 5, 8

  }

}
