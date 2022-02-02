package com.knox.leetcode.number;

import org.junit.Test;

public class Test392 {

	@Test
	public void test() {
		System.out.println("ans: " + isSubsequence("abc", "ahbdgdc"));
	}

	public boolean isSubsequence(String s, String t) {
		int n = s.length(), m = t.length();

		// dp数组dp[i][j]表示字符串t以i位置开始第一次出现字符j的位置
		int[][] f = new int[m + 1][26];
		// 初始化边界条件，dp[i][j] = m表示t中不存在字符j
		for (int i = 0; i < 26; i++) {
			f[m][i] = m;
		}

		// 从后往前递推初始化dp数组
		for (int i = m - 1; i >= 0; i--) {
			for (int j = 0; j < 26; j++) {
				if (t.charAt(i) == j + 'a') f[i][j] = i;
				else f[i][j] = f[i + 1][j];
				System.out.println("f[" + t.charAt(i) + "][" + Character.valueOf((char) ('a' + j)) + "] = " + f[i][j]);
			}
		}
		int add = 0;
		for (int i = 0; i < n; i++) {
			// t中没有s[i] 返回false
			if (f[add][s.charAt(i) - 'a'] == m) {
				return false;
			}
			// 否则直接跳到t中s[i]第一次出现的位置之后一位
			add = f[add][s.charAt(i) - 'a'] + 1;
		}
		return true;
	}
}
