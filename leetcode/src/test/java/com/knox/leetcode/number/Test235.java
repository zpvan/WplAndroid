package com.knox.leetcode.number;

class Test235 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class Solution {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			TreeNode ans = root;
			while (ans != null) {
				if (ans.val > p.val && ans.val > q.val) {
					ans = ans.left;
				} else if (ans.val < p.val && ans.val < q.val) {
					ans = ans.right;
				} else {
					break;
				}
			}
			return ans;
		}
	}
}
