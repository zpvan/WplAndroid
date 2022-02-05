package com.knox.leetcode.number;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Test1162 {

	/**
	 * BFS queue+while
	 *
	 * @param grid
	 * @return
	 */
	public int maxDistance(int[][] grid) {
		int n = grid.length;
		Deque<int[]> queue = new ArrayDeque<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					queue.addLast(new int[]{i, j});
					map.put(i * n + j, 0);
				}
			}
		}
		int ans = -1;
		int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		while (!queue.isEmpty()) {
			int[] cur = queue.pollFirst();
			int dx = cur[0], dy = cur[1];
			int step = map.get(dx * n + dy);
			for (int[] di : dirs) {
				int nx = dx + di[0], ny = dy + di[1];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (grid[nx][ny] != 0) continue;
				grid[nx][ny] = step + 1;
				map.put(nx * n + ny, step + 1);
				queue.addLast(new int[]{nx, ny});
				ans = Math.max(ans, step + 1);
			}
		}
		return ans;
	}
}
