package com.knox.leetcode.number;

public class Test700 {

	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null || root.val == val) return root;
		TreeNode left = searchBST(root.left, val);
		return left != null ? left : searchBST(root.right, val);
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
