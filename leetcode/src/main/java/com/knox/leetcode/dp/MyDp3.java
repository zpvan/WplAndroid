package com.knox.leetcode.dp;

public class MyDp3 {

	/**
	 * 计算两个字符串之间的编辑距离
	 * 莱文斯坦距离: 增、删、改
	 * 最长公共子串长度: 增、删
	 *
	 * "mitcmu"与"mtacnu"的莱文斯坦距离是3, 最长公共子串长度是4
	 */

	/**
	 * 莱文斯坦距离的回溯算法实现
	 * 如果a[i]与b[j]不匹配, 则有多种处理方式可选:
	 * 1、可以删除a[i], 然后递归考察a[i+1]和b[j]
	 * 2、可以删除b[j], 然后递归考察a[i]和b[j+1]
	 * 3、可以在a[i]前面添加一个跟b[j]相同的字符, 然后递归考察a[i]和b[j+1]
	 * 4、可以在b[j]前面添加一个跟a[i]相同的字符, 然后递归查考a[i+1]和b[j]
	 * 5、可以将a[i]替换成b[j], 或者将b[j]替换成a[i], 然后递归考察a[i+1]和b[j+1]
	 */
	private char[] a = "mitcmu".toCharArray();
	private char[] b = "mtacnu".toCharArray();
	private int n = 6;
	private int m = 6;
	private int minDist = Integer.MAX_VALUE; // 存储结果

	public void lwstBT(int i, int j, int edist) { // 调用方式 lwstBT(0, 0, 0);
		if (i == n || j == m) {
			if (i < n) edist += (n - i);
			if (j < m) edist += (m - j);
			if (edist < minDist) minDist = edist;
			return;
		}
		if (a[i] == b[j]) { // 两个字符匹配
			lwstBT(i + 1, j + 1, edist);
		} else { // 两个字符不匹配
			lwstBT(i + 1, j, edist + 1); // 删除a[i]或者b[j]前添加一个字符
			lwstBT(i, j + 1, edist + 1); // a[i]前添加一个字符或者删除b[j]
			lwstBT(i + 1, j + 1, edist + 1); // 讲a[i]和b[j]替换为相同字符
		}
	}

	public int lwstDP(char[] a, int n, char[] b, int m) {
		int[][] minDist = new int[n][m];
		for (int j = 0; j < m; j++) { // 初始化第0行:a[0..0]和b[0..j]的编辑距离
			if (a[0] == b[j]) minDist[0][j] = j;
			else if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
			else minDist[0][j] = 1;
		}
		for (int i = 0; i < n; i++) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
			if (a[i] == b[0]) minDist[i][0] = i;
			else if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
			else minDist[i][0] = 1;
		}
		// 动态规划
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (a[i] == b[j])
					minDist[i][j] = min(minDist[i - 1][j] + 1,
							minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
				else minDist[i][j] = min(minDist[i - 1][j] + 1,
						minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
			}
		}
		return minDist[n - 1][m - 1];
	}

	private int min(int x, int y, int z) {
		int ans = x;
		if (ans > y) ans = y;
		if (ans > z) ans = z;
		return ans;
	}

	/**
	 * 最长公共子串长度的动态规划实现
	 * 如果a[i]与b[j]匹配, 最大公共子串长度加一
	 * 如果a[i]与b[j]不匹配, 最大公共子串长度不变, 且有多种处理方式可选:
	 * 1、删除a[i], 或者在b[j]前面加上一个字符a[i], 然后递归考察a[i+1]和b[j]
	 * 2、删除b[j], 或者在a[i]前面加上一个字符b[j], 然后递归考察a[i]和b[j+1]
	 */
	public int lcs(char[] a, int n, int[] b, int m) {
		int[][] maxlcs = new int[n][m];
		for (int j = 0; j < m; j++) { // 初始化第0行:a[0..0]与b[0..j]的maxlcs
			if (a[0] == b[j]) maxlcs[0][j] = 1;
			else if (j != 0) maxlcs[0][j] = maxlcs[0][j - 1];
			else maxlcs[0][j] = 0;
		}
		for (int i = 0; i < n; i++) { // 初始化第0列:a[0..i]与b[0..0]的maxlcs
			if (a[i] == b[0]) maxlcs[i][0] = 1;
			else if (i != 0) maxlcs[i][0] = maxlcs[i - 1][0];
			else maxlcs[i][0] = 0;
		}

		// 动态规划
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (a[i] == b[j])
					maxlcs[i][j] = max(maxlcs[i - 1][j],
							maxlcs[i][j - 1], maxlcs[i - 1][j - 1] + 1);
				else maxlcs[i][j] = max(maxlcs[i - 1][j],
						maxlcs[i][j - 1], maxlcs[i - 1][j - 1]);
			}
		}
		return maxlcs[n - 1][m - 1];
	}

	private int max(int x, int y, int z) {
		int ans = x;
		if (ans < y) ans = y;
		if (ans < z) ans = z;
		return ans;
	}
}
