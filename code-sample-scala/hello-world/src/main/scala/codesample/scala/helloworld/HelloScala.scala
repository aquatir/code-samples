package codesample.scala.helloworld

object HelloScala extends App {

  // fibonacci sequence calculator
  def fib(fibNumberPosition: Int): Int = {

    def fibIterator(iter: Int, currentLesser: Int, currentHigher: Int) : Int = {
      if (fibNumberPosition == iter)
        currentHigher
      else
        fibIterator(iter + 1, currentHigher, currentLesser + currentHigher)
    }

    fibIterator(1, 0, 1)
  }

  // Newton's square root func
  def newtonSqrt(x: Double) : Double = {

    def newtonItr(x: Double, guess: Double): Double = {
      if (isGoodEnough(x, guess)) guess
      else newtonItr(x, improve(x, guess))
    }

    def abs(x: Double) = {if (x < 0) -x else x}
    def isGoodEnough(x: Double, guess: Double): Boolean = {
      abs(x - guess*guess) < 0.001 }
    def improve(x: Double, guess: Double) : Double = {
      (guess + x/guess) / 2
    }

    newtonItr(x, 1)

  }

  println("Hello, Scala!")
  println(fib(3)) // 1 1 2 3 5 8 13
  println(newtonSqrt(2))
}
