package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test436 {

	@Test
	public void test() {
		System.out.println("ans: " + Arrays.toString(findRightInterval(new int[][]{{1, 2}})));
		System.out.println("ans: " + Arrays.toString(findRightInterval(new int[][]{{3, 4}, {2, 3}, {1, 2}})));
		System.out.println("ans: " + Arrays.toString(findRightInterval(new int[][]{{1, 4}, {2, 3}, {3, 4}})));
	}

	public int[] findRightInterval(int[][] intervals) {
		int len = intervals.length;
		int[] ans = new int[len];

		int[][] startAndPos = new int[len][2];
		for (int i = 0; i < len; i++) {
			startAndPos[i][0] = intervals[i][0];
			startAndPos[i][1] = i;
		}
		Arrays.sort(startAndPos, (o1, o2) -> {
			return o1[0] - o2[0];
		});

		for (int i = 0; i < len; i++) {
			int end_i = intervals[i][1];
			ans[i] = binarySearch(startAndPos, end_i);
		}
		return ans;
	}

	private int binarySearch(int[][] sorted, int t) {
		// 二分查找
		int l = 0, r = sorted.length - 1;
		while (l <= r) {
			int mid = l + ((r - l) >> 1);
			int start_i = sorted[mid][0];
			if (start_i >= t) {
				if ((mid == 0) || (sorted[mid - 1][0] < t)) return sorted[mid][1];
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return -1;
	}
}
