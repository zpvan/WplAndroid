package com.knox.leetcode.number;

import org.junit.Test;

public class Test713 {

	@Test
	public void test() {
		System.out.println("ans = " + numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
	}

	public int numSubarrayProductLessThanK(int[] nums, int k) {
		// 滑动窗口
		int ans = 0;
		for (int end = 0, start = 0, prod = 1; end < nums.length; end++) {
			prod *= nums[end];
			while (start <= end && prod >= k) {
				prod /= nums[start++];
			}
			ans += end - start + 1;
		}
		return ans;
	}
}
