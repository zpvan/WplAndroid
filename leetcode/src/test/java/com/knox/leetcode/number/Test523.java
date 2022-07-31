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
		System.out.println("check: " + checkSubarraySum2(nums, k));
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

	/**
	 * 前缀和 prefix-sum，描述的是数组的每个元素的前面元素的和，显然，前缀和也是一个数组
	 * 如果事先计算出数组 nums 的前缀和数组，则对于任意一个子数组，都可以在 O(1)O(1) 的时间内得到其元素和。
	 * 譬如用 prefixSum[i] 表示数组 nums 从下标0到下标i的前缀和，则 nums 从下标 p+1 到下标 q 的子数组长度为 q-p，
	 * 该子数组的元素和为 prefixSum[q] - prefixSum[p]，也可以用 Map 来存，方便索引
	 */
	public boolean checkSubarraySum2(int[] nums, int k) {
		if (nums.length < 2) {
			return false;
		}
		// key 是对k求余， value 是元素位置
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int prefixSum = 0;
		for (int i = 0; i < nums.length; i++) {
			prefixSum = (prefixSum + nums[i]) % k;
			// 检查有没有同余的
			if (map.containsKey(prefixSum)) {
				Integer prefixIndex = map.get(prefixSum);
				if ((i + 1) - prefixIndex >= 2) return true;
			} else {
				map.put(prefixSum, i + 1);
			}
		}

		return false;
	}
}
