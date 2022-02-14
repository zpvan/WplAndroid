package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test1380 {

	public List<Integer> luckyNumbers(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		List<Integer> ans = new ArrayList<>();
		Set<Integer> perhaps = new HashSet<>();
		// 在同一行的所有元素中最小
		for (int i = 0; i < m; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				min = Math.min(matrix[i][j], min);
			}
			perhaps.add(min);
		}
		// 在同一列的所有元素中最大
		for (int j = 0; j < n; j++) {
			int max = 0;
			for (int i = 0; i < m; i++) {
             	max = Math.max(matrix[i][j], max);
			}
			if (perhaps.contains(max)) {
				ans.add(max);
			}
		}
		return ans;
	}
}
