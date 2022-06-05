package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test473 {

	@Test
	public void test() {
		System.out.println("ans: " + makesquare(new int[]{1, 1, 2, 2, 2})); // true
		System.out.println("ans: " + makesquare(new int[]{3, 3, 3, 3, 4})); // false
	}

	int[] ms;
	int sideLength;
	public boolean makesquare(int[] matchsticks) {
		// DFS + 剪枝

		int sum = Arrays.stream(matchsticks).sum();
		// 不能乘除4, 返回false
		if ((sum & 0x03) != 0) return false;
		sideLength = sum / 4;

		Arrays.sort(matchsticks); // 从小到大排序
		ms = matchsticks;
		return dfs(matchsticks.length - 1, new int[4]);
	}

	private boolean dfs(int idx, int[] square) {
		// 当前这轮用 ms[idx], 分别试试4条边

		// 如果用完最后一根火柴, 表示刚好能拼成一个正方行, 因为拼的时候有剪枝
		if (idx == -1) return true;

		for (int i = 0; i < 4; i++) {
			square[i] += ms[idx];
			// 剪枝
			if (square[i] <= sideLength && dfs(idx - 1, square)) {
				return true;
			}
			square[i] -= ms[idx];
		}
		return false;
	}
}
