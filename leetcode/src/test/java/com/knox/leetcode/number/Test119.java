package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Test119 {
	public List<Integer> getRow(int rowIndex) {
		int[] dp = new int[rowIndex + 1];

		dp[0] = 1;

		for (int i = 1; i <= rowIndex; i++) {
			for (int j = i; j >= 0; j--) {
				if (j == i || j == 0) {
					dp[j] = 1;
					continue;
				}
				dp[j] = dp[j] + dp[j - 1];
			}
		}

		List<Integer> ans = new ArrayList<>();
		Arrays.stream(dp).forEach(it -> ans.add(it));
		return ans;
	}
}
