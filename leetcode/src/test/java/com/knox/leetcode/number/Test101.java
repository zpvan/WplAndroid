package com.knox.leetcode.number;

public class Test101 {
	public class TreeNode {
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
		public boolean isSymmetric(TreeNode root) {
			return symmetric(root, root);
		}

		private boolean symmetric(TreeNode left, TreeNode right) {
			if (left == null && right == null) {
				return true;
			}
			if (left == null || right == null) {
				return false;
			}
			return (left.val == right.val)
					&& symmetric(left.left, right.right)
					&& symmetric(left.right, right.left);
		}
	}
}
