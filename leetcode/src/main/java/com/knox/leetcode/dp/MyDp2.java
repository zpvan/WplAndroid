package com.knox.leetcode.dp;

public class MyDp2 {

	/**
	 * 一个模型三个特性
	 * 模型: 多阶段决策最优解模型
	 * 特性: 最优子结构、无后效性、重复子问题
	 * 最优子结构: 后面阶段的状态可以通过前面阶段的状态推导出来
	 * 无后效性: 1、推导状态时, 无需考虑后面阶段的状态。2、某阶段一旦确定, 就不受之后阶段的决策影响
	 * 重复子问题: 不同的决策序列, 到达某个相同的阶段时, 可能会产生重复的状态
	 * <p>
	 * 两种解题思路
	 * 1、状态转移表法
	 * 一般是二维, 每个状态包含三个变量, 行、列、数组值。
	 * 2、状态转移方程法
	 */

	private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

	// 回溯算法实现
	private int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量
	private int n = 4;
	private int[][] mem = new int[4][4];

	// 调用方式 minDistBacktracing(0, 0, 0, w, n);
	public void minDistBT(int i, int j, int dist, int[][] w, int n) {
		// 到达了{n-1,n-1}这个位置了
		if (i == n && j == n) {
			if (dist < minDist) minDist = dist;
			return;
		}
		if (i < n) { // 往下走, 更新i=i+1, j=j
			minDistBT(i + 1, j, dist + w[i][j], w, n);
		}
		if (j < n) { // 往右走, 更新i=i, j=j+1
			minDistBT(i, j + 1, dist + w[i][j], w, n);
		}
	}

	public int minDistDP(int[][] matrix, int n) {
		// 状态转移表法
		int[][] states = new int[n][n];
		int sum = 0;
		for (int j = 0; j < n; j++) { // 初始化states的第一行数据
			sum += matrix[0][j];
			states[0][j] = sum;
		}
		sum = 0;
		for (int i = 0; i < n; i++) { // 初始化states的第一列数据
			sum += matrix[i][0];
			states[i][0] = sum;
		}
		// 动态规划
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				states[i][j] = matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
			}
		}
		return states[n - 1][n - 1];
	}

	public int minDist(int i, int j) { // 调用minDist(i-1, j-1)
		if (i == 0 && j == 0) return matrix[0][0];
		if (mem[i][j] > 0) return mem[i][j];
		int minLeft = Integer.MAX_VALUE;
		if (j - 1 >= 0) {
			minLeft = minDist(i, j - 1);
		}
		int minUp = Integer.MAX_VALUE;
		if (i - 1 >= 0) {
			minUp = minDist(i - 1, j);
		}

		int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
		mem[i][j] = currMinDist;
		return currMinDist;
	}
}
