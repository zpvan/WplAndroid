package com.knox.leetcode.number;

import org.junit.Test;

public class Test5 {

	@Test
	public void test() {
		System.out.println("ans: " + longestPalindrome("babad"));
		System.out.println("ans: " + longestPalindrome("cbbd"));
	}

	public String longestPalindrome(String s) {
		int length = s.length();
		if (length == 0) {
			return "";
		}

		boolean[][] dp = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			dp[i][i] = true;
		}

		int begin = 0;
		int maxLength = 0;
		char[] ca = s.toCharArray();
		for (int l = 2; l <= length; l++) {
			for (int i = 0; i < length; i++) {
				int j = i + l - 1;
				if (j >= length) {
					break;
				}
				if (ca[i] != ca[j]) {
					dp[i][j] = false;
				} else {
					// ca[i] == ca[j]
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				if (dp[i][j] && l > maxLength) {
					maxLength = l;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + maxLength);
	}

	@Test
	public void test2() {
		System.out.println("ans: " + longestPalindrome2("babad"));
		System.out.println("ans: " + longestPalindrome2("cbbd"));
	}

	public String longestPalindrome2(String s) {
		/**
		 * dp
		 * boolean[][] dp = new boolean[N][N];
		 * dp[i][j] = true 描述下标从i到j的子串是回文子串
		 * 状态转移方程如下:
		 * dp[i][j] = dp[i+1][j-1] & (s[i] == s[j])
		 * 初始化(也就是边界)
		 * dp[i][i] = true
		 * dp[i][i+1] = s[i] == s[i+1]
		 */
		int N = s.length();
		// 特殊情况
		if (N < 2) return s;
		boolean[][] dp = new boolean[N][N];
		// 初始化
		for (int i = 0; i < N; i++) {
			dp[i][i] = true;
		}

		// 枚举子串长度
		int maxLen = 1;
		int begin = 0;
		for (int L = 2; L <= N; L++) {
			// 枚举左边界
			for (int i = 0; i < N; i++) {
				// 由L和i可以确定右边界, 即j=i+L-1, 得
				int j = i + L - 1;
				// j 不合法则退出
				if (j >= N) break;
				// 状态转移方程
				if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				} else {
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				if (dp[i][j] & L > maxLen) {
					begin = i;
					maxLen = L;
				}
			}
		}
		return s.substring(begin, begin + maxLen);
	}
}
