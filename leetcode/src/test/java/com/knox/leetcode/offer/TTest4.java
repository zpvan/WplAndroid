package com.knox.leetcode.offer;

import org.junit.Test;

import java.util.HashMap;

public class TTest4 {

	HashMap<Integer, Integer> map = new HashMap<>();

	@Test
	public void test() {
		long start = System.currentTimeMillis();
    	System.out.println("sum: " + numWays(25) + "\n cost: " + (System.currentTimeMillis() - start));
	}

	private int numWays(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = i;
			for (int j = 0; j < i; j++) {
				int left = Math.max(0, j - 1);
				int right = Math.max(0, i - 1 - j - 1);
				dp[i] += dp[left] + dp[right];
			}
		}
		return dp[n];
	}
}
