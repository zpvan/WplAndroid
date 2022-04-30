package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.List;

public class Test1305 {


	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		// 中序遍历
		List<Integer> l1 = new ArrayList<>();
		inOrder(root1, l1);
		List<Integer> l2 = new ArrayList<>();
		inOrder(root2, l2);

		List<Integer> ans = new ArrayList<>();
		int p1 = 0, p2 = 0;
		while (p1 < l1.size() && p2 < l2.size()) {
			
		}

		return l1;
	}

	private void inOrder(TreeNode node, List<Integer> ans) {
		if (node == null) {
			return;
		}
		inOrder(node.left, ans);
		ans.add(node.val);
		inOrder(node.right, ans);
	}

	private static class TreeNode {
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
