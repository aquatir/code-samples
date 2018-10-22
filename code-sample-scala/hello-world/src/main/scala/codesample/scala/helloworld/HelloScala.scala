package codesample.scala.helloworld

object HelloScala extends App {

  def fib(fibNumberPosition: Int): Int = {

    def fibIterator(iter: Int, currentLesser: Int, currentHigher: Int) : Int = {
      if (fibNumberPosition == iter)
        currentHigher
      else
        fibIterator(iter + 1, currentHigher, currentLesser + currentHigher)
    }

    fibIterator(1, 0, 1)
  }

  println("Hello, Scala!")
  println(fib(3)) // 1 1 2 3 5 8 13
}
