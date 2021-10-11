package com.codesample.leetcode.medium

fun main() {
    // [5,9,1,3,5,7] -> false
    val root1 = TreeNode(5);
    val childLeft1 = TreeNode(9);
    val childRight1 = TreeNode(1);

    root1.left = childLeft1;
    root1.right = childRight1;

    childLeft1.left = TreeNode(3)
    childLeft1.right = TreeNode(5)
    childRight1.left = TreeNode(7)

    println(isEvenOddTree(root1))


    // [1,10,4,3,null,7,9,12,8,6,null,null,2] -> true
    val root2 = TreeNode(5)
    root2.left = TreeNode(10)
    root2.left!!.left = TreeNode(3)
    root2.left!!.left!!.left = TreeNode(12)
    root2.left!!.left!!.right = TreeNode(8)

    root2.right = TreeNode(4)
    root2.right!!.left = TreeNode(7)
    root2.right!!.right = TreeNode(9)
    root2.right!!.left!!.left = TreeNode(6)
    root2.right!!.right!!.right = TreeNode(2)

    println(isEvenOddTree(root2))

    // [2,12,8,5,9,null,null,18,16]

    val root3 = TreeNode(2)
    root3.left = TreeNode(12)
    root3.right = TreeNode(8)

    root3.left!!.left = TreeNode(5)
    root3.left!!.right = TreeNode(9)
    root3.left!!.left!!.left = TreeNode(8)
    root3.left!!.left!!.right = TreeNode(16)

    println(isEvenOddTree(root3))

    // [11,18,14,3,7,null,null,null,null,18,null,6]



}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/**
 * 1609 Even Odd Tree https://leetcode.com/problems/even-odd-tree
 *
 * A binary tree is named Even-Odd if it meets the following conditions:
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 */

// Ideally, a normal BFS should be used here instead of building a list of children on each iteration.
fun isEvenOddTree(root: TreeNode?): Boolean {

    /** odd = 1, 3, 5... */
    fun isOdd(value: Int) = value % 2 == 1

    /** even = 2, 4, 6...*/
    fun isEven(value: Int) = !isOdd(value)

    fun createChildrenList(checkList: List<TreeNode>): List<TreeNode> {
        val mutableChildList = mutableListOf<TreeNode>()

        for (element in checkList) {
            if (element.left != null) {
                mutableChildList.add(element.left!!)
            }
            if (element.right != null) {
                mutableChildList.add(element.right!!)
            }
        }

        return mutableChildList
    }

    // descending and each element is even
    fun elementsCorrectOnOddLevel(roots: List<TreeNode>): Boolean {
        if (roots.size == 1) {
            return isEven(roots[0].`val`)
        }
        for (i in 0..roots.size - 2) {
            if (roots[i].`val` > roots[i + 1].`val` && isEven(roots[i].`val`) && isEven(roots[i + 1].`val`)) {
                //
            } else {
                return false
            }
        }
        return true
    }

    // ascending and each element is odd
    fun elementsCorrectOnEvenLevel(roots: List<TreeNode>): Boolean {
        if (roots.size == 1) {
            return isOdd(roots[0].`val`)
        }
        for (i in 0..roots.size - 2) {
            if (roots[i].`val` < roots[i + 1].`val` && isOdd(roots[i].`val`) && isOdd(roots[i + 1].`val`)) {
                //
            } else {
                return false
            }
        }
        return true
    }

    fun isValidChecklist(roots: List<TreeNode>, currentLevel: Int): Boolean {
        val isOddLevel = isOdd(currentLevel)

        if (isOddLevel) {
            return elementsCorrectOnOddLevel(roots)
        } else {
            return elementsCorrectOnEvenLevel(roots)
        }
    }


    if (root == null || isEven(root.`val`)) {
        return false;
    }

    var currentLevel = 1;
    var checkList: List<TreeNode> = listOf(root);
    while (true) {
        checkList = createChildrenList(checkList)
        if (checkList.isEmpty()) {
            return true
        }

        if (!isValidChecklist(checkList, currentLevel)) {
            return false
        }
        currentLevel++
    }
}
