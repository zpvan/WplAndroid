package com.knox.leetcode.number;

import org.junit.Test;

public class Test69 {

	@Test
	public int mySqrt(int x) {
		int low = 0;
		int high = x;
		int ans = -1;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);
			if ((long) mid * mid <= x) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return ans;
	}
}
