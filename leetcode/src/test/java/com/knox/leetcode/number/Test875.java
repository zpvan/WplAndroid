package com.knox.leetcode.number;

import org.junit.Test;

import java.util.stream.IntStream;

public class Test875 {

	@Test
	public void test() {
		System.out.println("ans: " + minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
		System.out.println("ans: " + minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
		System.out.println("ans: " + minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
	}

	public int minEatingSpeed(int[] piles, int h) {
		int low = 1;
		int high = IntStream.of(piles).max().getAsInt();
		int ans = high;
		while (low < high) { // l < r
			int mid = low + ((high - low) >> 1); // mid = l + ((r - l) >> 1)
			long time = getTime(piles, mid);
			if (time <= h) {
				ans = mid;
				high = mid; // r = mid
			} else {
				low = mid + 1; // l = mid + 1
			}
		}
		return ans;
	}

	private long getTime(int[] piles, int speed) {
		long time = 0;
		for (int pile : piles) {
			int curTime = (pile + speed - 1) / speed;
			time += curTime;
		}
		return time;
	}
}
