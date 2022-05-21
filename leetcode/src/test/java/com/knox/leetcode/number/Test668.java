package com.knox.leetcode.number;

class Test668 {

	public int findKthNumber(int m, int n, int k) {
		int left = 1, right = m * n;
		while (left < right) {
			int x = left + (right - left) / 2;
			int count = x / n * n;
			for (int i = x / n + 1; i <= m; ++i) {
				count += x / i;
			}
			if (count >= k) {
				right = x;
			} else {
				left = x + 1;
			}
		}
		return left;
	}

	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		int left = matrix[0][0];
		int right = matrix[n - 1][n - 1];
		while (left < right) {
			int mid = left + ((right - left) >> 1);
			if (check(matrix, mid, k, n)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public boolean check(int[][] matrix, int mid, int k, int n) {
		int i = n - 1;
		int j = 0;
		int num = 0;
		while (i >= 0 && j < n) {
			if (matrix[i][j] <= mid) {
				num += i + 1;
				j++;
			} else {
				i--;
			}
		}
		return num >= k;
	}
}
