package com.knox.leetcode.number;

import org.junit.Test;

public class Test2104 {

	@Test
	public void test() {
		System.out.println("ans: " + subArrayRanges(new int[]{1, 2, 3}));
		System.out.println("ans: " + subArrayRanges(new int[]{1, 3, 3}));
		System.out.println("ans: " + subArrayRanges(new int[]{4, -2, -3, 4, 1}));
	}

	public long subArrayRanges(int[] nums) {
		long ans = 0;
		for (int i = 0; i < nums.length; i++) {
			int max = nums[i];
			int min = nums[i];
			for (int j = i - 1; j >= 0; j--) {
				max = Math.max(max, nums[j]);
				min = Math.min(min, nums[j]);

				ans += max - min;
			}
		}
		return ans;
	}
}
