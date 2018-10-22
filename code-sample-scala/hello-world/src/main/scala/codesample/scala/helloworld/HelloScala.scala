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

    def newtonItr(guess: Double): Double = {
      if (isGuessGoodEnough(guess)) guess
      else newtonItr(improveGuess(guess))
    }

    def abs(x: Double) = {if (x < 0) -x else x}
    def isGuessGoodEnough(guess: Double): Boolean = {
      abs(x - guess*guess) / x < 0.001 }
    def improveGuess(guess: Double) : Double = {
      (guess + x/guess) / 2
    }

    newtonItr(1.0)
  }

  println("Hello, Scala!")
  println(fib(3)) // 1 1 2 3 5 8 13
  println(newtonSqrt(2))
  println(newtonSqrt(4))
  println(newtonSqrt(5e-10))
  println(newtonSqrt(1e50))
}
