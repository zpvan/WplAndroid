package com.knox.leetcode.number;

public class Test1037 {

	public boolean isBoomerang(int[][] points) {
		int[] p1 = points[0];
		int[] p2 = points[1];
		int[] p3 = points[2];

		if (isSameX(p1, p2)) return !isSameX(p3, p1) && !isSameY(p1, p2);
		if (isSameX(p1, p3)) return !isSameY(p1, p3);

		float d2 = ((float) p2[1] - p1[1]) / (p2[0] - p1[0]);
		float d3 = ((float) p3[1] - p1[1]) / (p3[0] - p1[0]);

		return d2 != d3;
	}

	public boolean isSameX(int[] p1, int[] p2) {
		return p1[0] == p2[0];
	}

	public boolean isSameY(int[] p1, int[] p2) {
		return p1[1] == p2[1];
	}
}
