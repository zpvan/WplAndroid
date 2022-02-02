package com.knox.leetcode.number;

import org.junit.Test;

import java.util.PriorityQueue;

public class Test215 {

	@Test
	public void test() {
		// [3,2,1,5,6,4]
		// 2
		int[] nums = new int[]{3, 2, 1, 5, 6, 4};
		int k = 2;
		System.out.println("" + findKthLargest(nums, k));
	}

	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
		for (int num : nums) {
			queue.add(num);
			if (queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}
}
