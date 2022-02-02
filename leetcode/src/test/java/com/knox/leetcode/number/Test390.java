package com.knox.leetcode.number;

import org.junit.Test;

public class Test390 {

	@Test
	public void test() {
		System.err.println("ans = " + lastRemaining(10000000));
	}

	public int lastRemaining(int n) {
		// 每轮剩余的数字都是等差数列, 且step=2^(round-1), remain=(n/2^(round-1)), round计算轮数
		int ans = 1, step = 1, remain = n, round = 1;
		while (remain > 1) {
			if ((round & 1) != 0) ans = ans + step; // 左向右
			else ans = ((remain & 1) == 0) ? ans : ans + step; // 右向左; 偶数?奇数
			round++;
			step <<= 1;
			remain >>= 1;
		}
		return ans;
	}
}
