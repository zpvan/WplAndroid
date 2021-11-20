package com.knox.leetcode.number;

import java.util.Arrays;

public class Test594 {
	public int findLHS(int[] nums) {
		Arrays.sort(nums);
		int begin = 0, ans = 0;
		for (int end = 0; end < nums.length; end++) {
			if (nums[end] - nums[begin] > 1) {
				begin++;
			}
			if (nums[end] - nums[begin] == 1) {
				ans = Math.max(ans, end - begin + 1);
			}
		}
		return ans;
	}
}
