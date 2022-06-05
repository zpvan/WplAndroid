package com.knox.leetcode.number;

public class Test98 {

	public boolean isValidBST(TreeNode root) {
   		return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean dfs(TreeNode root, long left, long right) {
		if (root.val <= left) return false;
		if (root.val >= right) return false;
		if (root.left != null) {
			if (!dfs(root.left, left, root.val)) return false;
		}
		if (root.right != null) {
			if (!dfs(root.right, root.val, right)) return false;
		}
		return true;
	}

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
}
