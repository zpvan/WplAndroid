package com.knox.leetcode.number;

import org.junit.Test;

public class Test492 {

	@Test
	public void test() {
		int area = 122122;
		int[] lw = constructRectangle(area);
		System.err.println("l: " + lw[0] + ", w: " + lw[1] + ", area: " + area);
	}

	public int[] constructRectangle(int area) {
		int w = (int) Math.sqrt(area);
		for (int i = w; i > 0; i--) {
			if (area % i == 0) return new int[]{area / i, i};
		}
		return new int[]{area, 1};
	}
}
