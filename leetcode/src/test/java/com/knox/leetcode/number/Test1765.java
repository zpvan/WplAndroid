package com.knox.leetcode.number;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Test1765 {

	// BFS
	public int[][] highestPeak(int[][] isWater) {

		int m = isWater.length;
		int n = isWater[0].length;

		int[][] ans = new int[m][n];
		Deque<int[]> queue = new ArrayDeque<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (1 == isWater[i][j]) {
					ans[i][j] = 0;
					queue.addLast(new int[]{i, j});
					map.put(i * n + j, 0);
				} else ans[i][j] = -1;
			}
		}

		int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		while (!queue.isEmpty()) {
			int[] poll = queue.pollFirst();
			int dx = poll[0], dy = poll[1];
			int high = map.get(dx * n + dy);
			for (int[] dir : dirs) {
				int x = dx + dir[0], y = dy + dir[1];
				if (x < 0 || x >= m || y < 0 || y >= n) continue;
				if (ans[x][y] == -1) {
					map.put(x * n + y, high + 1);
					queue.addLast(new int[]{x, y});
					ans[x][y] = high + 1;
				}

			}
		}

		return ans;
	}
}
