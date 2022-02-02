package com.knox.leetcode.number;

import java.util.Arrays;

class Test221 {
	public int maximalSquare(char[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;

		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
			}
		}
		print(dp);

		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == '0') {
					continue;
				}
				// matrix[i][j] == 1
				dp[i][j] = 1;
				if (max == 0) {
					max = 1;
				}
				if (i > 0 && j > 0) {
					if (matrix[i - 1][j - 1] == '1' && matrix[i - 1][j] == '1' && matrix[i][j - 1] == '1') {
						dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
						if (dp[i][j] > max) {
							max = dp[i][j];
						}
					}
				}
			}
		}
		print(dp);

		return max * max;
	}

	private void print(int[][] dp) {
		Arrays.stream(dp)
				.forEach(
						line -> {
							StringBuilder sb = new StringBuilder();
							for (int i = 0; i < line.length; i++) {
								sb.append(line[i] + " ");
							}
							System.out.println(sb);
						});
		System.out.println("");
	}
}
