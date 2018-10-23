package codesample.scala.coursera.course1.week2

import scala.annotation.tailrec

object RationalMaths {

  class Rational(x: Int, y: Int) {

    require(y != 0, "denominator should not be zero")
    def numenator: Int = x / gcd(x,y)
    def denominator: Int = y / gcd(x,y)

    // scala constructor!
    def this(x: Int) = this (x, 1)


    @tailrec
    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    def + (other: Rational): Rational = {
      new Rational(this.numenator * other.denominator + this.denominator * other.numenator,
        denominator * other.denominator)
    }

    def unary_-() : Rational = new Rational(- this.numenator, this.denominator)

    def - (other: Rational): Rational = {
      this + (-other)
    }

    override def toString: String = {
      s"${this.numenator}/${this.denominator}"
    }
  }


  def main(args: Array[String]): Unit = {

    val rational = new Rational(4, 5) + new Rational(2,10)
    println(rational)
    println(new Rational(4))
    val infixFuncRational = new Rational(4,5) + new Rational(5,5)
    println(infixFuncRational)
    println( - new Rational(4,4))
    println(new Rational(1,4) - (- new Rational(3,3)))
  }


}
