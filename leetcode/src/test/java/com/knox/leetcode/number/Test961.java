package com.knox.leetcode.number;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test961 {

	@Test
	public void test() {
		System.out.println("ans: " + repeatedNTimes(new int[]{1, 2, 3, 3}));
		System.out.println("ans: " + repeatedNTimes(new int[]{2, 1, 2, 5, 3, 2}));
		System.out.println("ans: " + repeatedNTimes(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
	}

	public int repeatedNTimes(int[] nums) {
		Map<Integer, Boolean> map = new HashMap<>();
		for (int num : nums) {
			Boolean b = map.getOrDefault(num, Boolean.FALSE);
			if (b) return num;
			map.put(num, Boolean.TRUE);
		}
		return -1;
	}
}
