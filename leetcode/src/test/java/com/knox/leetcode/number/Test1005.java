package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test1005 {

	@Test
	public void test() {
		int[] nums = new int[]{2, -3, -1, 5, -4};
		int k = 2;
		System.err.println("ans: " + largestSumAfterKNegations(nums, k));
	}

	public int largestSumAfterKNegations(int[] nums, int k) {
		PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(s -> Integer.parseInt(s.split("&")[0])));
		int[] absMinValueAndPos = new int[]{Integer.MAX_VALUE, -1};
		for (int i = 0; i < nums.length; i++) {
			// 找出所有负数 和 绝对值最小的数
			if (nums[i] < 0) pq.add(nums[i] + "&" + i);
			if (Math.abs(nums[i]) < absMinValueAndPos[0]) {
				absMinValueAndPos[0] = Math.abs(nums[i]);
				absMinValueAndPos[1] = i;
			}
		}
		int min = Math.min(k, pq.size());
		for (int i = 0; i < min; i++) {
			int pos = Integer.parseInt(pq.poll().split("&")[1]);
			nums[pos] = -nums[pos];
		}
		if ((k > min) && ((k - min) & 1) == 1) {
			nums[absMinValueAndPos[1]] = -nums[absMinValueAndPos[1]];
		}
		return Arrays.stream(nums).sum();
	}
}
