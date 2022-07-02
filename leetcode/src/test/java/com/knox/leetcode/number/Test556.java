package com.knox.leetcode.number;

import org.junit.Test;

public class Test556 {

	@Test
	public void test() {
		System.out.println("ans: " + nextGreaterElement(12));
		System.out.println("ans: " + nextGreaterElement(21));
		System.out.println("ans: " + nextGreaterElement(11));
		System.out.println("ans: " + nextGreaterElement(1234));
	}

	public int nextGreaterElement(int n) {
		char[] nums = Integer.toString(n).toCharArray();
		int i = nums.length - 2;
		// 从后往前找, 找到第一个不符合"从大到小"排序的数字, 索引为i
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		if (i < 0) {
			return -1;
		}

		// 从i往后找, 找到第一个大于cs[i]的数的索引j
		int j = nums.length - 1;
		while (j >= 0 && nums[i] >= nums[j]) {
			j--;
		}

		// 交换位置i与位置j的数字
		swap(nums, i, j);

		// 从位置i后的数开始, 从小到大排序, 由于原本是从大到小排序, 所以reverse即可
		reverse(nums, i + 1);
		long ans = Long.parseLong(new String(nums));
		return ans > Integer.MAX_VALUE ? -1 : (int) ans;
	}

	public void reverse(char[] nums, int begin) {
		int i = begin, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	public void swap(char[] nums, int i, int j) {
		char temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
