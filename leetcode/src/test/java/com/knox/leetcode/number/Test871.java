package com.knox.leetcode.number;

import org.junit.Test;

public class Test871 {

	@Test
	public void test() {
		System.out.println("ans: " + minRefuelStops(1, 1,
				new int[][]{}));
		System.out.println("ans: " + minRefuelStops(100, 1,
				new int[][]{{10, 100}}));
		System.out.println("ans: " + minRefuelStops(100, 10,
				new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
	}

	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		/**
		 * stations[i][0] 描述加油站位置
		 * stations[i][1] 描述加油站剩余油量
		 *
		 * dp[i] 描述加i次后, 可走的最大距离
		 */

		int sLen = stations.length;
		int[] dp = new int[sLen + 1];

		// 初始化
		dp[0] = startFuel;

		// 动态规划
		for (int i = 0; i < sLen; i++) { // 遍历加油站, 因为输入已经排好序
			for (int j = i; j >= 0; j--) { // 遍历dp, 找到dp[0]~dp[j+1]的最大值
				if (dp[j] >= stations[i][0]) {
					// 如果加第j次油之后, 可以开到 stations[i][1]
					dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
				}
			}
		}

		// 遍历答案
		for (int i = 0; i <= sLen; i++) {
			if (dp[i] >= target) return i;
		}
		return -1;
	}
}
