package com.knox.leetcode.number;

import org.junit.Test;

public class Test926 {

	@Test
	public void test() {
		System.out.println("ans: " + minFlipsMonoIncr("00110"));
		System.out.println("ans: " + minFlipsMonoIncr("010110"));
		System.out.println("ans: " + minFlipsMonoIncr("00011000"));
	}

	public int minFlipsMonoIncr(String s) {
		/**
		 * dp
		 * int[][] dp = new dp[N][2]
		 * dp[i][0] 描述下标i是0时, dp值是最小翻转次数
		 * dp[i][1] 描述下标i是1时, dp值是最小翻转次数
		 * 状态转移方程
		 * dp[i][0] = dp[i-1][0] + (s[i] == '0' ? 0 : 1)
		 * dp[i][1] = min(dp[i-1][0], dp[i-1][1]) + (s[i] == '1' ? 0 : 1)
		 */

		int N = s.length();
		int[][] dp = new int[N][2];
		char[] cs = s.toCharArray();
		// 初始化
		dp[0][0] = cs[0] == '0' ? 0 : 1;
		dp[0][1] = cs[0] == '1' ? 0 : 1;

		for (int i = 1; i < N; i++) {
			dp[i][0] = dp[i - 1][0] + (cs[i] == '0' ? 0 : 1);
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + (cs[i] == '1' ? 0 : 1);
		}

		return Math.min(dp[N - 1][0], dp[N - 1][1]);
	}
}
