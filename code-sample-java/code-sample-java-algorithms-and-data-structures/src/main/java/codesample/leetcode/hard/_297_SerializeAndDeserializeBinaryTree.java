package codesample.leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 297. Serialize and Deserialize Binary Tree — https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 */
public class _297_SerializeAndDeserializeBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Codes {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return recursiveSerialize(root, "");
        }

        // serialize to pre-order traversal format. Root => left => right
        private String recursiveSerialize(TreeNode root, String str) {
            // Recursive serialization.
            if (root == null) {
                str += "null,";
            } else {
                str += root.val + ",";
                str = recursiveSerialize(root.left, str);
                str = recursiveSerialize(root.right, str);
            }
            return str;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            var dataList = new LinkedList<String>(Arrays.asList(data.split(",")));
            return recursiveDeserialize(dataList);
        }

        public TreeNode recursiveDeserialize(List<String> l) {
            // Recursive deserialization.
            if (l.get(0).equals("null")) {
                l.remove(0);
                return null;
            }

            var root = new TreeNode(Integer.parseInt(l.get(0)));
            l.remove(0);
            root.left = recursiveDeserialize(l);
            root.right = recursiveDeserialize(l);

            return root;
        }
    }
}
