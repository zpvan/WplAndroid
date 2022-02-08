package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test2006 {

	@Test
	public void test() {
		System.err.println("ans: " + countKDifference(new int[]{1, 2, 2, 1}, 1));
	}

	public int countKDifference(int[] nums, int k) {
		int ans = 0;
		Arrays.sort(nums);
		int l = 0, r = 0;
		while (l <= r && r < nums.length) {
			int diff = nums[r] - nums[l];
			if (diff == k) {
				int _r = r;
				while (_r < nums.length && nums[_r++] == nums[r]) {
					ans++;
				}
				l++;
			} else if (diff < k) r++;
			else l++;
		}
		return ans;
	}
}
