package codesample.scala.coursera.course1.week3

object ConsListExample {

  trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
  }

  class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    override def isEmpty: Boolean = false
  }

  class Nil[T] extends List[T] {
    override def isEmpty: Boolean = true
    override def head: Nothing = throw new NoSuchFieldException("Nil.head")
    override def tail: Nothing = throw new NoSuchFieldException("Nil.tail")
  }


  def main(args: Array[String]): Unit = {
    def nth[T](index: Int, list: List[T]): T = {
      if (list.isEmpty) throw new IndexOutOfBoundsException(index + " not found")
      if (index == 0)
        list.head
      else
        nth(index - 1, list.tail)
    }

    val kek = new Cons(5, new Cons(4, new Cons(5, new Nil)))
    println(nth(2, kek))
    println(nth(-3, kek))
  }


}
