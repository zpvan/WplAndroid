package com.knox.leetcode.number;

import java.util.Arrays;

public class Test1051 {

	public int heightChecker(int[] heights) {
		int[] h_ = heights.clone();
		Arrays.sort(heights);
		int ans = 0;
		for (int i = 0; i < h_.length; i++) {
			ans += h_[i] != heights[i] ? 1 : 0;
		}
		return ans;
	}
}
