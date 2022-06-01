package com.knox.leetcode.number;

class Test1022 {


	public int sumRootToLeaf(TreeNode root) {
		return dfs(0, root);
	}

	private int dfs(int val, TreeNode root) {
		if (root == null) {
			return 0;
		}
        val = (val << 1) | root.val;
        if (root.left == null && root.right == null) {
           return val;
        }
		return dfs(val, root.left) + dfs(val, root.right);
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
