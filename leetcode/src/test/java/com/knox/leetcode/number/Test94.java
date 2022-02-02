package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test94 {

	@Test
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		inOrder(root, ans);
		return ans;
	}

	private void inOrder(TreeNode root, List<Integer> ans) {
		if (root == null) {
			return;
		}
		inOrder(root.left, ans);
		ans.add(root.val);
		inOrder(root.right, ans);
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