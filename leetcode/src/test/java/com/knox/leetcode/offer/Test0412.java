package com.knox.leetcode.offer;

import java.util.HashSet;
import java.util.Set;

public class Test0412 {

	private Set<String> ans = new HashSet<>();
	private int _sum = 0;

	public int pathSum2(TreeNode root, int sum) {
		// 回溯, 总有多判断的
		_sum = sum;
		f(root, 0, "00");
		return ans.size();
	}

	private void f(TreeNode root, int cs, String str) {
		if (root == null) return;

		if (cs + root.val == _sum) {
			System.out.println(str + "-" + root.val);
			String path = str + "-" + root.val;
			String _path = path.substring(path.lastIndexOf("00"));
			ans.add(_path);
			System.out.println("_path: " + _path);
			return;
		}
		// 选当前点
		f(root.left, cs + root.val, str + "-" + root.val);
		f(root.right, cs + root.val, str + "-" + root.val);
		// 不选当前点
		f(root.left, 0, str + "-00-l");
		f(root.right, 0, str + "-00-r");
	}

	public int pathSum(TreeNode root, int sum) {
		// dfs
		
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
