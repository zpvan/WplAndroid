package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test2055 {

	@Test
	public void test() {
		System.out.println("ans: " + Arrays.toString(platesBetweenCandles(
				"**|**|***|",
				new int[][]{{2, 5}, {5, 9}})));

		System.out.println("ans: " + Arrays.toString(platesBetweenCandles(
				"***|**|*****|**||**|*",
				new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
	}

	/**
	 * prefix-sum 前缀和
	 * 算出每个位置i前的"*"个数作为前缀和数组 prefixSum[i]
	 */
	public int[] platesBetweenCandles(String s, int[][] queries) {
		int n = s.length();
		int[] preSum = new int[n];
		for (int i = 0, sum = 0; i < n; i++) {
			if (s.charAt(i) == '*') {
				sum++;
			}
			preSum[i] = sum;
		}
		int[] left = new int[n];
		for (int i = 0, l = -1; i < n; i++) {
			if (s.charAt(i) == '|') {
				l = i;
			}
			left[i] = l;
		}
		int[] right = new int[n];
		for (int i = n - 1, r = -1; i >= 0; i--) {
			if (s.charAt(i) == '|') {
				r = i;
			}
			right[i] = r;
		}
		int[] ans = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int[] query = queries[i];
			int x = right[query[0]], y = left[query[1]];
			ans[i] = x == -1 || y == -1 || x >= y ? 0 : preSum[y] - preSum[x];
		}
		return ans;
	}
}
