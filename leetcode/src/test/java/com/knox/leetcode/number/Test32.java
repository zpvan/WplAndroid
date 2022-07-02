package com.knox.leetcode.number;

import java.util.Stack;

class Test32 {
	public int longestValidParentheses(String s) {
		return stackLongestValidParentheses(s);
	}

	private int stackLongestValidParentheses(String s) {
		int ans = 0;
		Stack<Integer> stack = new Stack<>();
		stack.add(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				// ')'
				stack.pop();
				if (stack.empty()) {
					stack.push(i);
				} else {
					ans = Math.max(ans, i - stack.peek());
				}
			}
		}
		return ans;
	}

	private int dpLongestValidParentheses(String s) {
		int ans = 0;
		int[] dp = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					// "....))"
					dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}
}
