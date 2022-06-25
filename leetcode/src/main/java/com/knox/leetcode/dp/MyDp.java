package com.knox.leetcode.dp;

public class MyDp {

	/**
	 * 动态规划(最优问题: 最大、最小、最长 等)
	 * <p>
	 * 总结一:
	 * 1、为什么需要动态规划
	 * 2、动态规划解题方法是如何演化出来的
	 * <p>
	 * 总结二:
	 * 1、动态规划适合的解决问题的特征
	 * 2、动态规划的解题思路
	 * 3、思辨(贪心、分治、回溯、动态规划)
	 * <p>
	 * 总结三:
	 * 1、三个经典的动态规划问题
	 */


	// 0-1 背包问题
	// 回溯实现
	private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
	private int[] weight = {2, 2, 4, 6, 3}; // 物品重量
	private int n = 5; // 物品个数
	private int w = 9; // 背包承受的最大重量
	// "备忘录"来优化回溯实现
	private boolean[][] mem = new boolean[5][10]; // 默认值false
	// 引入物品价值的升级版背包问题
	private int[] value = {3, 4, 8, 9, 6};
	private int maxV = Integer.MIN_VALUE; // 结果放到maxV中

	// 优化空间复杂度版本
	public static int knapsack2(int[] items, int n, int w) {
		boolean[] states = new boolean[w + 1];
		states[0] = true; // 第一行的数据要特殊处理, 可以利用哨兵优化
		if (items[0] <= w) {
			states[items[0]] = true;
		}

		for (int i = 1; i < n; i++) { // 动态规划
			for (int j = w - items[i]; j >= 0; j--) { // 把第i个物品放入背包
				if (states[j]) states[j + items[i]] = true;
			}
		}

		for (int i = w; i >= 0; i--) {
			if (states[i]) return i;
		}
		return 0;
	}

	public static int knapsack3(int[] weight, int[] value, int n, int w) {
		int[][] states = new int[n][w + 1];
		for (int i = 0; i < n; i++) { // 初始化states
			for (int j = 0; j < w + 1; j++) {
				states[i][j] = -1;
			}
		}
		states[0][0] = 0;
		if (weight[0] <= w) { // 第0个物品符合重量条件, 则放入背包
			states[0][weight[0]] = value[0];
		}
		for (int i = 1; i < n; i++) { // 动态规划, 状态转移
			for (int j = 0; j <= w; j++) { // 不选择第i个物品
				if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
			}
			for (int j = 0; j <= w - weight[i]; j++) { // 选择第i个物品
				if (states[i - 1][j] >= 0) {
					int v = states[i - 1][j] + value[i];
					if (v > states[i][j + weight[i]]) {
						states[i][j + weight[i]] = v;
					}
				}
			}
		}
		// 找出最大值
		int maxvalue = -1;
		for (int j = 0; j <= w; j++) {
			if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
		}
		return maxvalue;
	}

	// 淘宝购物车凑单问题
	// items商品价格, n商品个数, w表示满减条件, 比如200
	public static void double11advance(int[] items, int n, int w) {
		boolean[][] states = new boolean[n][3 * w + 1]; // 超过3倍就没有薅羊毛的价值了
		states[0][0] = true; // 第一行的数据要特殊处理
		if (items[0] <= 3 * w) {
			states[0][items[0]] = true;
		}
		for (int i = 1; i < n; i++) { // 动态规划
			for (int j = 0; j <= 3 * w; ++j) { // 不购买第i个商品
				if (states[i - 1][j]) states[i][j] = states[i - 1][j];
			}
			for (int j = 0; j <= 3 * w - items[i]; j++) { // 购买第i个商品
				if (states[i - 1][j]) states[i][items[i]] = true;
			}
		}

		int j;
		for (j = w; j < 3 * w + 1; j++) {
			if (states[n - 1][j]) break; // 输出结果大于等于w的最小值
		}
		if (j == 3 * w + 1) return; // 没有可行解
		for (int i = n - 1; i >= 1; --i) { // i 表示二维数组中的行, j表示列
			if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
				System.out.println(items[i] + " ");
				j = j - items[i];
			} // else 没有购买这个商品, j不变
		}
		if (j != 0) System.out.println(items[0]);
	}

	// i表示将要决策第几个物品是否装入背包
	// cw表示当前背包中物品的总重量
	private void f(int i, int cw) { // 调用f(0, 0)
		if (cw == w || i == n) { // cw==w表示装满了, i==n表示物品都考察完了
			if (cw > maxW) maxW = cw;
			return;
		}
		f(i + 1, cw); // 选择不装第i个物品
		if (cw + weight[i] <= w) {
			f(i + 1, cw + weight[i]); // 选择装第i个物品
		}
	}

	private void f2(int i, int cw) {
		if (cw == w || i == n) { // cw==w表示装满了, i==n表示物品都考察完了
			if (cw > maxW) maxW = cw;
			return;
		}
		if (mem[i][cw]) return; // 重复状态
		mem[i][cw] = true; // 记录(i, cw)这个状态
		f(i + 1, cw); // 选择不装第i个物品
		if (cw + weight[i] <= w) {
			f(i + 1, cw + weight[i]); // 选择装第i个物品
		}
	}

	// 动态规划实现。weight: 物品重量, n:物品个数, w:背包客承载重量
	public int knapsack(int[] weight, int n, int w) {
		boolean[][] states = new boolean[n][w + 1]; // 默认值false
		states[0][0] = true; // 第一行的数据要特殊处理, 可以利用哨兵优化
		if (weight[0] <= w) {
			states[0][weight[0]] = true;
		}

		// 动态规划状态转移
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= w; ++j) { // 不把第i个物品放入背包
				if (states[i - 1][j] = true) states[i][j] = states[i - 1][j];
			}
			for (int j = 0; j <= w - weight[i]; ++j) { // 把第i个物品放入背包
				if (states[i - 1][j] = true) states[i][j + weight[j]] = true;
			}
		}

		for (int i = w; i >= 0; i--) { // 输出结果
			if (states[n - 1][i]) return i;
		}

		return 0;
	}

	public void f3(int i, int cw, int cv) {
		if (cw == w || i == n) {
			if (cv > maxV) maxV = cv;
			return;
		}
		f3(i + 1, cw, cv); // 选择不装第i个物品
		if (cw + weight[i] <= w) {
			f3(i + 1, cw + weight[i], cv + value[i]); // 选择装第i个物品
		}
	}
}
