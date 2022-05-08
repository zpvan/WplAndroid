package com.knox.leetcode.number;

import java.util.TreeSet;

public class Test220 {

	/**
	 * abs(nums[i] - nums[j]) <= t
	 * abs(i - j) <= k
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i - k; j < nums.length; j++) {
				if (j < 0) continue;
				if (Math.abs((long) nums[i] - nums[j]) <= t) return true;
			}
		}
		return false;
	}

	public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
		int n = nums.length;
		TreeSet<Long> ts = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			Long u = nums[i] * 1L;
			// 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
			Long l = ts.floor(u);
			// 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
			Long r = ts.ceiling(u);
			if(l != null && u - l <= t) return true;
			if(r != null && r - u <= t) return true;
			// 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
			ts.add(u);
			if (i >= k) ts.remove(nums[i - k] * 1L);
		}
		return false;
	}
}
