package codesample.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 437. Path Sum III — https://leetcode.com/problems/path-sum-iii/
 *
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 */
public class _437_PathSumIII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // approach 1: Store all the possible sums before this one
    // check if any matches the target

    private int result = 0;
    public int pathSum(TreeNode root, int targetSum) {
        // pass a list of all possible sums in a subtree above to each node.
        // max length of that array will be [length] of a tree

        var possibleSums = new ArrayList<Long>();
        pathSum(root, targetSum, possibleSums);
        return result;
    }

    public void pathSum(TreeNode node, int targetSum, List<Long> possibleSums) {
        if (node == null) {
            return;
        }

        // new possible sums:
        // - this node
        // - any of the possible previous sums + value of this node
        var newPossibleSums = new ArrayList<Long>();
        newPossibleSums.add( (long) node.val);
        for (var sum: possibleSums) {
            newPossibleSums.add( (long) node.val + sum);
        }

        // see if any of the possible sums adds up to target for this node
        for (var sum: newPossibleSums) {
            if (sum == targetSum) {
                result++;
            }
        }


        // traverse both to the left and to the right
        pathSum(node.left, targetSum, newPossibleSums);
        pathSum(node.right, targetSum, newPossibleSums);
    }

    // approach 2: store all the nodes before this one
    // and count the sum each time

//    public int pathSum(TreeNode root, int targetSum) {
//        List<Integer> possibleSums = new ArrayList<>();
//        return pathSum(root, targetSum, possibleSums);
//    }
//
//    public int pathSum(TreeNode node, int targetSum, List<Integer> possibleSums) {
//        if (node == null) {
//            return 0;
//        }
//
//        // see if any of existing paths are good
//        possibleSums.add(node.val);
//        int correctPathCount = 0;
//        long sum = 0;
//
//        for (int i = possibleSums.size() - 1; i >= 0; i--) {
//            sum += possibleSums.get(i);
//            if (sum == targetSum) {
//                correctPathCount += 1;
//            }
//        }
//
//        correctPathCount += pathSum(node.left, targetSum, possibleSums);
//        correctPathCount += pathSum(node.right, targetSum, possibleSums);
//
//        // while traversing back from DFS, remove current node from the list of nodes
//        possibleSums.remove(possibleSums.size() - 1);
//
//        return correctPathCount;
//    }

    public static void main(String[] args) {
        var s = new _437_PathSumIII();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = null;
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(2);

        System.out.println(s.pathSum(root, 3));
    }
}
