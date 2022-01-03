package com.knox.leetcode.number;

import org.junit.Test;

public class Test390 {

	@Test
	public void test() {
		System.err.println("ans = " + lastRemaining(10000000));
	}

	public int lastRemaining(int n) {
		int a1 = 1;
		int k = 0, cnt = n, step = 1;
		while (cnt > 1) {
			if (k % 2 == 0) { // 正向
				a1 = a1 + step;
			} else { // 反向
				a1 = (cnt % 2 == 0) ? a1 : a1 + step;
			}
			k++;
			cnt = cnt >> 1;
			step = step << 1;
		}
		return a1;
	}
}
