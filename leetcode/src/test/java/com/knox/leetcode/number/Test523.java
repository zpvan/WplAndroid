package com.knox.leetcode.number;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test523 {

	@Test
	public void test() {
		// [23,2,4,6,6]
		// 7
		int[] nums = new int[]{23, 2, 4, 6, 6};
		int k = 7;
		System.out.println("check: " + checkSubarraySum(nums, k));
	}

	public boolean checkSubarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		map.put(0, -1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// 同余定理
			if (map.containsKey(sum % k)) {
				System.out.println("同余: " + (sum % k) + ", i1: " + i
						+ ", i2: " + map.get(sum % k));
				if (i - map.get(sum % k) < 2) {
					continue;
				}
				return true;
			}
			map.put(sum % k, i);
			System.out.println("add key: " + (sum % k) + ", value: " + i
					+ ", 余: " + (sum % k));
		}
		return false;
	}
}
