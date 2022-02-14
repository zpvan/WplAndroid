package com.knox.leetcode.number;

import org.junit.Test;

public class Test540 {

	@Test
	public void test() {
		System.err.println("ans: " + singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 10, 11}));
	}

	public int singleNonDuplicate(int[] nums) {
		int l = 0, r = nums.length - 1;
		int ans = 0;
		while (l <= r) {
			int mid = (r - l) / 2 + l;
			System.err.println("debug<< l: " + l + ", r: " + r + ", mid: " + mid);
			if ((l == r)
					|| (mid == l && nums[mid] != nums[mid + 1])
					|| (mid == r && nums[mid] != nums[mid - 1])) {
				ans = nums[mid];
				break;
			}
			// mid > l && mid < r
			if (nums[mid] == nums[mid + 1]) {
				int left_length = mid - l;
				if ((left_length & 1) == 1) {
					r = mid - 1;
				} else {
					l = mid + 2;
				}
			} else if (nums[mid] == nums[mid - 1]) {
				int left_length = mid - 1 - l;
				if ((left_length & 1) == 1) {
					r = mid - 2;
				} else {
					l = mid + 1;
				}
			} else {
				ans = nums[mid];
				break;
			}
		}
		return ans;
	}
}
