package com.knox.leetcode.number;

public class Test110 {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  class Solution {
    public boolean isBalanced(TreeNode root) {
      return height(root) >= 0;
    }

    int height(TreeNode node) {
      if (node == null) {
        return 0;
      }
      int lh = height(node.left);
      int rh = height(node.right);
      if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
        return -1;
      }
      return Math.max(lh, rh) + 1;
    }
  }
}
