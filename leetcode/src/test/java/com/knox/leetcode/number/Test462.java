package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test462 {

	@Test
	public void test() {
		System.err.println("ans: " + minMoves2(new int[]{1, 2, 3}));
		System.err.println("ans: " + minMoves2(new int[]{1, 10, 2, 9}));
	}

	public int minMoves2(int[] nums) {
		Arrays.sort(nums);
		int avg =  nums[nums.length / 2];
		int ans = 0;
		for (int num : nums) {
			ans += Math.abs(num - avg);
		}
		return ans;
	}
}
