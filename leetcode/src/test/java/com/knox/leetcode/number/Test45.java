package com.knox.leetcode.number;

import org.junit.Test;

public class Test45 {

	@Test
	public void test() {
		int[] nums = new int[]{2, 3, 1, 1, 4};
		System.out.println("jump: " + jump(nums));
	}

	public int jump(int[] nums) {
		int[] dp = new int[nums.length];

		dp[0] = 0;
		for (int i = 0; i < nums.length; i++) {
			int step = nums[i];
			for (int j = step; j > 0; j--) {
				if (i + j >= nums.length) continue;
				if (i + j == nums.length - 1) return dp[i] + 1;
				if (dp[i + j] == 0) dp[i + j] = dp[i] + 1;
				else break;
			}
		}
		return dp[0];
	}
}
