package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test532 {

	@Test
	public void test() {
		System.out.println("ans: " + findPairs(new int[]{3, 1, 4, 1, 5}, 2));
		System.out.println("ans: " + findPairs(new int[]{1, 2, 3, 4, 5}, 1));
		System.out.println("ans: " + findPairs(new int[]{1, 3, 1, 5, 4}, 0));
	}

	public int findPairs(int[] nums, int k) {
		int[] sorted = Arrays.stream(nums).sorted().toArray();
		Set<Integer> ans = new HashSet<>();
		for (int i = 0; i < sorted.length; i++) {
			for (int j = i + 1; j < sorted.length; j++) {
				if (sorted[j] - sorted[i] == k) {
					ans.add(sorted[i] + sorted[j]);
				} else if (sorted[j] - sorted[i] > k) {
					break;
				}
			}
		}
		return ans.size();
	}
}
