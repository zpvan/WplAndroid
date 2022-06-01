package com.knox.leetcode.number;

class Test450 {


	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) return null;

		TreeNode curr = root;
		while (curr != null) {
			if (curr.val == key) {
				if (curr.right != null) {
					// 右节点的最左节点
					TreeNode p = curr;
					TreeNode t = curr.right;
					while (t.left != null) {
						p = t;
						t = t.left;
					}
					if (p == curr) {
						// 右子节点没有左子节点
						curr.val = t.val;
						curr.right = t.right;
					} else {
						// 找到右子节点的最大左子节点
						curr.val = t.val;
						p.left = null;
					}
				} else if (curr.left != null) {
					// 左节点的最右节点
					TreeNode p = curr;
					TreeNode t = curr.left;
					while (t.right != null) {
						p = t;
						t = t.right;
					}
					if (p == curr) {
						// 左子节点没有右子节点
						curr.val = t.val;
						curr.left = t.left;
					} else {
						// 找到左子节点的最大右子节点
						curr.val = t.val;
						p.right = null;
					}
				}
			} else {
				if (key < curr.val) curr = curr.left;
				if (key > curr.val) curr = curr.right;
			}
		}

		return root;
	}

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
}
