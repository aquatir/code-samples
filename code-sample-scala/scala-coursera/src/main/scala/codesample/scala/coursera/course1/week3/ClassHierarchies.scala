package codesample.scala.coursera.course1.week3


abstract class IntSet {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

object EmptySet extends IntSet {
  override def contains(x: Int): Boolean = false
  override def incl(x: Int): IntSet = new NonEmptySet(x, EmptySet, EmptySet)
  override def union(other: IntSet): IntSet = other

  override def toString: String = "."
}

class NonEmptySet(element: Int, left: IntSet, right: IntSet) extends IntSet {

  override def contains(x: Int): Boolean =
    if (x < element) left contains x
    else if (x > element) right contains x
    else true

  override def incl(x: Int): IntSet =
    if (x < element) new NonEmptySet(element, left incl x, right)
    else if (x > element) new NonEmptySet(element, left, right incl x)
    else this

  override def union(other: IntSet): IntSet = (left union right union other) incl element

  override def toString: String = "{" + left  + element + right + "}"
}

object ClassHierarchies {

  def main(args: Array[String]): Unit = {

    val set = new NonEmptySet(3, EmptySet, EmptySet)
    val anotherSet = set.incl(3).incl(3).incl(3).incl(5).incl(11)
    val yetAnotherSet = set.incl(4).incl(6).incl(12)
    val unionOfSets = anotherSet.union(yetAnotherSet)

    println("Set include 11: " + anotherSet.contains(11))
    println(unionOfSets.toString)
  }


}
