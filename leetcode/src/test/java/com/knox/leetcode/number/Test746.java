package com.knox.leetcode.number;

import org.junit.Test;

public class Test746 {

	@Test
	public void test() {
		int[] cost = {/*10, 15, 20*/1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		System.err.println("minCostClimbingStairs: " + minCostClimbingStairs(cost));
	}

	public int minCostClimbingStairs(int[] cost) {
		int n = cost.length;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 0;
		for (int i = 2; i < n + 1; i++) {
			dp[i] = Math.min(cost[i - 2] + dp[i - 2], cost[i - 1] + dp[i - 1]);
		}
		return dp[n];
	}
}
