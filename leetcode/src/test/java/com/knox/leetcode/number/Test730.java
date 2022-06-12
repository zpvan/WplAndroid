package com.knox.leetcode.number;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test730 {

	@Test
	public void test() {
		System.out.println("ans: " + countPalindromicSubsequences("bccb"));
	}

	public int countPalindromicSubsequences(String s) {
		// dp + Set<String>

		Set<String> set = new HashSet<>();

		int L = s.length();
		boolean[][] dp = new boolean[L][L];
		for (int i = 0; i < L; i++) {
			dp[i][i] = true;
			set.add(String.valueOf(s.charAt(i)));
		}

		for (int len = 2; len <= L; len++) {
			for (int i = 0; i < L; i++) {
				int j = i + len - 1;
				if (j >= L) break;
				if (s.charAt(i) != s.charAt(j)) {
					dp[i][j] = false;
				} else {
					// s[i] == s[j]
					if (j - i < 3) {
						// len = [2,3]
						dp[i][j] = true;
					} else {
						// len >= 4
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				if (dp[i][j]) set.add(s.substring(i, j + 1));
			}
		}

		System.err.println("count In");
		for (String str : set) {
			System.out.println(str);
		}
		System.err.println("count Out");

		return set.size() % 1000000007;
	}
}
