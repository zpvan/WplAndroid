package com.knox.leetcode.number;

import java.util.LinkedList;

class Test239 {

	public int[] maxSlidingWindow(int[] nums, int k) {
		int[] ans = new int[nums.length - k + 1];
		LinkedList<Integer> ll = new LinkedList<>();
		for (int i = 0; i < nums.length; i++) {
			while (ll.size() > 0) {
				if (nums[ll.peekLast()] < nums[i]) ll.pollLast();
				else if (ll.peek() < i - k) ll.pop();
				else break;
			}
			ll.add(i);
			if (i + 1 >= k) {
				ans[i + 1 - k] = nums[ll.peek()];
			}
		}
		return ans;
	}
}
