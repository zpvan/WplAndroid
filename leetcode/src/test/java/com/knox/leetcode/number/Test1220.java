package com.knox.leetcode.number;

import java.util.Arrays;

public class Test1220 {

	/**
	 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
	 * 每个元音 'a' 后面都只能跟着 'e'
	 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
	 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
	 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
	 * 每个元音 'u' 后面只能跟着 'a'
	 *
	 * @param n
	 * @return
	 */
	public int countVowelPermutation(int n) {
		int MOD = (int) (1e9 + 7);
		// dp[i][j] 表示长度为i+1且结尾为j的字符串的个数
		long[][] dp = new long[n][5];
		Arrays.fill(dp[0], 1); // 1个字符的情况, 每种结尾只有1种情况
		for (int i = 1; i < n; i++) {
			// 每个元音 'a' 后面都只能跟着 'e'
			dp[i][1] += dp[i - 1][0];
			// 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
			dp[i][0] += dp[i - 1][1];
			dp[i][2] += dp[i - 1][1];
			// 每个元音 'i' 后面 不能 再跟着另一个 'i'
			dp[i][0] += dp[i - 1][2];
			dp[i][1] += dp[i - 1][2];
			dp[i][3] += dp[i - 1][2];
			dp[i][4] += dp[i - 1][2];
			// 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
			dp[i][2] += dp[i - 1][3];
			dp[i][4] += dp[i - 1][3];
			// 每个元音 'u' 后面只能跟着 'a'
			dp[i][0] += dp[i - 1][4];

			for (int j = 0; j < 5; j++) {
				dp[i][j] = dp[i][j] % MOD;
			}
		}
		long ans = 0;
		for (int j = 0; j < 5; j++) {
			ans += dp[n - 1][j];
		}
		return (int) (ans % MOD);
	}
}
