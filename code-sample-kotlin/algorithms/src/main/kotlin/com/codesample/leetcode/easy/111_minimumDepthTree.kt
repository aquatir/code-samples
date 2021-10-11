package com.codesample.leetcode.easy


fun main() {
    val s = Solution()

    //Input: root = [3,9,20,null,null,15,7]
    //Output: 2
    val root2 = TreeNode(3)
    root2.left = TreeNode(9)
    root2.right = TreeNode(20)
    root2.right!!.left = TreeNode(15)
    root2.right!!.right = TreeNode(7)

    println(s.minDepth(root2))

    //Input: root = [2,null,3,null,4,null,5,null,6]
    //Output: 5
    val root1 = TreeNode(2)
    root1.right = TreeNode(3)
    root1.right!!.right = TreeNode(4)
    root1.right!!.right!!.right = TreeNode(5)
    root1.right!!.right!!.right!!.right = TreeNode(5)
    println(s.minDepth(root1))

}


class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/**
 *  111. Minimum Depth of Binary Tree https://leetcode.com/problems/minimum-depth-of-binary-tree/
 *
 *  Given a binary tree, find its minimum depth.
 *  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *  Note: A leaf is a node with no children.
 */
class Solution {

    // DFS -> go down until need to go back up.
    // When starting to traverse a subtree and existing minLength is < then possible -> stop traversing.
    fun minDepth(root: TreeNode?): Int {

        var minimum = Integer.MAX_VALUE

        fun curMinOrLess(node: TreeNode, curLength: Int): Int {
            if (curLength >= minimum) {
                return minimum
            }

            if (node.left == null && node.right == null) {
                return curLength
            }


            if (node.left != null) {
                minimum = Math.min(minimum, curMinOrLess(node.left!!, curLength + 1))
            }
            if (node.right != null) {
                minimum = Math.min(minimum, curMinOrLess(node.right!!, curLength + 1))
            }

            return minimum
        }


        if (root == null) return 0

        return curMinOrLess(root, 1)
    }

    fun minDepth2(root: TreeNode?): Int {

        if (root == null) return 0

        if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1
        } else {
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1
        }
    }
}
