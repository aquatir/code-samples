package codesample.scala.coursera.course1.week4

object GenericsAgain {

  /*
UPPER
^
|
MIDDLE
^
|
LOWER
 */

  class Upper { override def toString: String = "Upper called" }
  class Middle extends Upper { override def toString: String = "Middle called" }
  class Lower extends Middle { override def toString: String = "Lower called" }

  class GenericValue[T](val value: T) {
    def print() = println(value.toString)
  }

  // This says T should be a subtype of Middle
  def middleAndSubClassesAcceptor[T <: Middle](instance: GenericValue[T]): Unit = instance.print()


  // This says either T should be a supertype of Middle. Or speaking the other way Middle should be a subtype of T
  def middleAndSuperClassesAcceptor[T >: Middle](instance: GenericValue[T]): Unit = instance.print()



  def main(args: Array[String]): Unit = {

    val lower = new Lower
    val middle = new Middle
    val upper = new Upper

    val genericLower = new GenericValue(lower)
    val genericMiddle = new GenericValue(middle)
    val genericUpper = new GenericValue(upper)

    middleAndSubClassesAcceptor(genericLower)
    middleAndSubClassesAcceptor(genericMiddle)
    //middleAndSubClassesAcceptor(genericUpper) // This will not compile because upper is supertype of middle. Not a subtype

    println("\nbetween\n")

    //middleAndSuperClassesAcceptor(genericLower) //This will not compile because lower is subtype of middle. Not supertype
    middleAndSuperClassesAcceptor(genericMiddle)
    middleAndSuperClassesAcceptor(genericUpper)

  }

}
