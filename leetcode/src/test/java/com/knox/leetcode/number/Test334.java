package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test334 {

	@Test
	public void test() {
		System.err.println("increasingTriplet: " + increasingTriplet(new int[]{2, 3, 9, 7, 6}));
	}

	public boolean increasingTriplet(int[] nums) {
		int n = nums.length, ans = 1;
		int[] f = new int[n + 1];
		Arrays.fill(f, 1111111);
		System.err.println("f: " + Arrays.toString(f));// log f
		for (int i = 0; i < n; i++) {
			int t = nums[i];
			int l = 1, r = i + 1;
			while (l < r) {
				int mid = (l + r) >> 1;
				// log i, l, r, mid
				System.err.println("i: " + i + ", l: " + l + ", r: " + r + ", mid: " + mid + ", f[mid]: " + f[mid]);
				if (f[mid] >= t) r = mid;
				else l = mid + 1;
			}
			f[r] = t;
			// log r, t, f
			System.err.println("r: " + r + ", t: " + t + ", f: " + Arrays.toString(f));
			ans = Math.max(ans, r);
		}
		System.err.println("ans: " + ans);
		return ans >= 3;
	}
}
