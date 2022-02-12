package com.knox.leetcode.number;

import java.util.Arrays;

public class Test1984 {

	public int minimumDifference(int[] nums, int k) {
		Arrays.sort(nums);
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length - (k - 1); i++) {
			int diff = nums[i + k - 1] - nums[i];
			ans = Math.min(ans, diff);
		}
		return ans;
	}
}
