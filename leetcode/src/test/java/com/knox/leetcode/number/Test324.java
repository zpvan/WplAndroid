package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test324 {

	@Test
	public void test() {
		System.out.println("ans: " + Arrays.toString(wiggleSort(new int[]{1, 1, 3})));
		System.out.println("ans: " + Arrays.toString(wiggleSort(new int[]{1, 5, 1, 1, 6, 4})));
		System.out.println("ans: " + Arrays.toString(wiggleSort(new int[]{1, 3, 2, 2, 3, 1})));
	}

	public int[] wiggleSort(int[] nums) {
		int[] clone = nums.clone();
		Arrays.sort(clone);
		int firstMax = (clone.length + 1) / 2;
		for (int i = 0; i < firstMax; i++) {
			nums[2 * i] = clone[i];
			if (2 * i + 1 < clone.length) {
				nums[2 * i + 1] = clone[i + firstMax];
			}
		}
		return nums;
	}
}
