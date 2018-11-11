package codesample.scala.coursera.course1.week4

object ObjectOrientedNaturalNumber {

  abstract class Nat {
    def isZero: Boolean
    def predecessor: Nat
    def successor: Nat = new Succ(this)
    def +(that: Nat): Nat
    def -(that: Nat): Nat
  }

  object Zero extends Nat {

    override def isZero: Boolean = true
    override def predecessor: Nat = throw new IllegalArgumentException("Zero.predecessor called")
    override def +(that: Nat): Nat = that
    override def -(that: Nat): Nat = if (that.isZero) Zero else throw new IllegalArgumentException("Zero.- negative number")

    override def toString: String = "0"
  }


  class Succ(n: Nat) extends Nat {
    override def isZero: Boolean = false
    override def predecessor: Nat = n
    override def +(that: Nat): Nat = new Succ(n+that)
    override def -(that: Nat): Nat = if (that.isZero) this else n - that.predecessor

    override def toString: String = "not 0"
  }


  def main(args: Array[String]): Unit = {
    val number = new Succ(Zero)
    println(number)
    val inceremementedTwice = number.successor.successor
    var anotherOne = number.successor.successor.successor
    val yetAnother = inceremementedTwice + anotherOne
    println(yetAnother)

    // wtf is going on...
  }

}
