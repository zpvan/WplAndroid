package com.knox.leetcode.number;

class Test112 {
	private class TreeNode {
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

	class Solution {
		public boolean hasPathSum(TreeNode root, int targetSum) {
			if (root == null) {
				return false;
			}
			int res = targetSum - root.val;
			if (res == 0 && root.left == null && root.right == null) {
				return true;
			}
			return hasPathSum(root.left, res) || hasPathSum(root.right, res);
		}
	}
}
