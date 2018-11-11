package codesample.scala.coursera.course1.week4

object PatterMatchingFTW {

  trait Expr {
    def eval(e: Expr): Int = e match {
      case Number(n) => n
      case Sum(left, right) => eval(left) + eval(right)
      case Prod(left, right) => eval(left) * eval(right)
    }

    def display(e: Expr): String = e match {
      case Number(n) => n.toString
      case Sum(left, right) => "(" + display(left) + " + " + display(right) + ")"
      case Prod(left, right) => "(" + display(left) + " * " + display(right) + ")"
    }
  }

  case class Number(n: Int) extends Expr
  case class Sum(left: Expr, right: Expr) extends Expr
  case class Prod(left: Expr, right: Expr) extends Expr

  object Calculator {  // this self-call looks weird... Is it okay?
    def eval(e: Expr): Int = e.eval(e)
    def display(e: Expr): String = e.display(e)
  }

  def main(args: Array[String]): Unit = {
    val sum = Sum(Number(5), Prod(Number(5), Number(3)))
    println(Calculator.display(sum)+ " = "+ Calculator.eval(sum))
  }

}
