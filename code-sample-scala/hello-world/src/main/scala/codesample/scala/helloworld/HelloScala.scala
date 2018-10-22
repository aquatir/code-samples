package codesample.scala.helloworld

import scala.annotation.tailrec

object HelloScala extends App {

  // fibonacci sequence calculator
  def fib(fibNumberPosition: Int): Int = {

    @tailrec
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

    @tailrec
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

  // Tail-recursive factorial
  def factorial(x: Integer) : Integer = {

    @tailrec
    def factorialRunning(x: Integer, runningFactorialValue: Integer) : Integer = {
      if (x == 0)
        runningFactorialValue
      else
        factorialRunning(x-1, runningFactorialValue * x)
    }

    factorialRunning(x, 1)
  }

  def factorialNonTailRecursive(x: Integer) : Integer = {
    if (x == 0) 1
    else x * factorialNonTailRecursive(x - 1)
  }

  println("Hello, Scala!")
  println(fib(3)) // 1 1 2 3 5 8 13
  println(newtonSqrt(2))
  println(newtonSqrt(4))
  println(newtonSqrt(5e-10))
  println(newtonSqrt(1e50))
  println(factorial(5)) // 1 * 2 * 3 * 4 * 5 = 120
  println(factorialNonTailRecursive(5))
}
