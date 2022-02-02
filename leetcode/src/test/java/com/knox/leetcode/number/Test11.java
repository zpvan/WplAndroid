package com.knox.leetcode.number;

import org.junit.Test;

public class Test11 {

	@Test
	public void test() {
		int[] height = new int[]{1, 2};
		System.out.println("maxArea: " + maxArea(height));
	}

	public int maxArea(int[] height) {
		int ans = 0;
		if (height.length < 2) {
			return 0;
		}
		int l = 0;
		int r = height.length - 1;
		while (l < r) {
			ans = Math.max(ans, Math.min(height[l], height[r]) * (r - l));
			if (height[l] < height[r]) {
				l++;
			} else {
				r--;
			}
		}

		return ans;
	}
}
