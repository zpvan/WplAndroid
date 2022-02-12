package com.knox.leetcode.number;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test1020 {

	public int numEnclaves(int[][] grid) {
		int ans = 0;
		// bfs
//		ans = numEnclavesWithBFS(grid);

		// dfs
//		ans = numEnclavesWithDFS(grid);

		// 并查集 + dfs
		ans = numEnclavesWithDisjointDFS(grid);

		return ans;
	}

	private int numEnclavesWithDisjointDFS(int[][] grid) {

		return 0;
	}

	private int numEnclavesWithDFS(int[][] grid) {
		// stack + recursive
		int m = grid.length, n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1 && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
					visited[i][j] = true;
					dfs(i, j, grid, visited);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) ans++;
			}
		}
		return ans;
	}

	private void dfs(int x, int y, int[][] grid, boolean[][] visited) {
		int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for (int[] dir : dirs) {
			int x_ = x + dir[0], y_ = y + dir[1];
			if (x_ >= 0 && x_ < grid.length && y_ >= 0 && y_ < grid[0].length) {
				if (visited[x_][y_]) continue;
				if (grid[x_][y_] == 0) continue;
				visited[x_][y_] = true;
				dfs(x_, y_, grid, visited);
			}
		}
	}

	private int numEnclavesWithBFS(int[][] grid) {
		// queue + while
		int m = grid.length, n = grid[0].length, ans = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) continue;
				ans++;
				if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
					queue.addLast(new int[]{i, j});
					visited[i][j] = true;
				}
			}
		}
		int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		while (!queue.isEmpty()) {
			int[] poll = queue.pollFirst();
			ans--;
			for (int[] dir : dirs) {
				int x_ = poll[0] + dir[0];
				int y_ = poll[1] + dir[1];
				if (x_ >= 0 && x_ < m && y_ >= 0 && y_ < n) {
					if (visited[x_][y_]) continue;
					if (grid[x_][y_] == 1) {
						queue.addLast(new int[]{x_, y_});
						visited[x_][y_] = true;
					}
				}
			}
		}
		return ans;
	}
}
