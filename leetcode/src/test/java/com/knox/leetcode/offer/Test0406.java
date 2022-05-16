package com.knox.leetcode.offer;

import java.util.ArrayList;
import java.util.List;

public class Test0406 {

	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		List<TreeNode> list = new ArrayList<>();
		inorder(root, list);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).val == p.val) {
				int ans = i + 1;
				return (ans) < list.size() ? list.get(ans) : null;
			}
		}
		return null;
	}

	private void inorder(TreeNode root, List<TreeNode> list) {
		if (root == null) return;
		inorder(root.left, list);
		list.add(root);
		inorder(root.right, list);
	}

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
