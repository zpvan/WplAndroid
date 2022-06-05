package com.knox.leetcode.number;

class Test450 {


	public TreeNode deleteNode2(TreeNode root, int key) {
		if (root == null) return null;

		TreeNode prev = null;
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
				} else {
					// 删掉
					if (prev == null) return null;
					if (prev.val > key) prev.left = null;
					if (prev.val < key) prev.right = null;
				}
				break;
			} else {
				prev = curr;
				int cval = curr.val;
				if (key < cval) curr = curr.left;
				if (key > cval) curr = curr.right;
			}
		}

		return root;
	}

	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.val > key) {
			root.left = deleteNode(root.left, key);
			return root;
		}
		if (root.val < key) {
			root.right = deleteNode(root.right, key);
			return root;
		}
		if (root.val == key) {
			if (root.left == null && root.right == null) {
				return null;
			}
			if (root.right == null) {
				return root.left;
			}
			if (root.left == null) {
				return root.right;
			}
			TreeNode successor = root.right;
			while (successor.left != null) {
				successor = successor.left;
			}
			root.right = deleteNode(root.right, successor.val);
			successor.right = root.right;
			successor.left = root.left;
			return successor;
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
