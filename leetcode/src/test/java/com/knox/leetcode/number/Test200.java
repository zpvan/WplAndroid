package com.knox.leetcode.number;

public class Test200 {

	/**
	 * '1' 表示陆地
	 * '0' 表示水
	 *
	 * @param grid
	 * @return
	 */

	int m, n;
	int[] dx = new int[]{0, 0, -1, 1};
	int[] dy = new int[]{-1, 1, 0, 0};

	public int numIslands(char[][] grid) {
		int ans = 0;

		m = grid.length;
		n = grid[0].length;
		boolean[][] visited = new boolean[m][n];

		int[] pos = new int[2];
		while (findLand(grid, pos)) {
			ans++;
			// dfs
			dfs(pos, grid, visited);
		}
		return ans;
	}

	private void dfs(int[] pos, char[][] grid, boolean[][] visited) {
		int x = pos[0], y = pos[1];

		if (visited[x][y]) return;
		visited[x][y] = true;
		if (grid[x][y] == '1') {
			grid[x][y] = '#';

			for (int i = 0; i < 4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];
				if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n) {
					dfs(new int[]{x1, y1}, grid, visited);
				}
			}
		}
	}

	private boolean findLand(char[][] grid, int[] pos) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					pos[0] = i;
					pos[1] = j;
					return true;
				}
			}
		}
		return false;
	}
}
