package com.knox.leetcode.number;

import java.util.LinkedList;
import java.util.Queue;

public class Test547 {

	/**
	 * 求无向图中的连通个数，一般的解题思路为DFS、BFS、并查集
	 */
	public int findCircleNum(int[][] isConnected) {
		int ans = 0;
		ans = findCircleNum_dfs(isConnected);

		ans = findCircleNum_bfs(isConnected);

		ans = findCircleNum_union_find_set(isConnected);

		return ans;
	}

	private int findCircleNum_union_find_set(int[][] isConnected) {
		// 初始化并查集
		UnionFind uf = new UnionFind(isConnected.length);
		// 遍历每个顶点，将当前顶点与其邻接点进行合并
		for (int i = 0; i < isConnected.length; i++) {
			for (int j = 0; j < isConnected.length; j++) {
				if (isConnected[i][j] == 1) {
					uf.union(i, j);
				}
			}
		}
		// 返回最终合并后的集合的数量
		return uf.size;
	}

	private int findCircleNum_bfs(int[][] isConnected) {
		// 元素个数
		int size = isConnected.length;
		// visited记录是否访问过
		boolean[] visited = new boolean[size];
		int ans = 0;
		// 遍历
		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 如果没有访问过，则用DFS去搜索连通元素
				bfs(i, isConnected, visited);
				ans++;
			}
		}
		return ans;
	}

	private void bfs(int s, int[][] isConnected, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			Integer poll = queue.poll();
			for (int i = 0; i < isConnected.length; i++) {
				if (isConnected[poll][i] == 1 && !visited[i]) {
					visited[poll] = true;
					queue.add(i);
				}
			}
		}
	}

	private int findCircleNum_dfs(int[][] isConnected) {
		// 元素个数
		int size = isConnected.length;
		// visited记录是否访问过
		boolean[] visited = new boolean[size];
		int ans = 0;
		// 遍历
		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				visited[i] = true;
				// 如果没有访问过，则用DFS去搜索连通元素
				dfs(i, isConnected, visited);
				ans++;
			}
		}
		return ans;
	}

	private void dfs(int s, int[][] isConnected, boolean[] visited) {
		for (int d = 0; d < isConnected.length; d++) {
			if (isConnected[s][d] == 1 && !visited[d]) {
				visited[d] = true;
				dfs(d, isConnected, visited);
			}
		}
	}

	private static class UnionFind {
		int[] roots;
		int size; // 集合数量

		UnionFind(int n) {
			roots = new int[n];
			for (int i = 0; i < n; i++) {
				roots[i] = i;
			}
			size = n;
		}

		public int find(int i) {
			if (roots[i] == i) {
				// 如果i节点的父节点是i, 就说明i是祖先节点了
				return i;
			}
			// 否则继续沿着父节点继续找祖先节点
			return find(roots[i]);
		}

		public void union(int p, int q) {
			int pZuXian = find(p);
			int qZuXian = find(q);

			if (pZuXian != qZuXian) {
				roots[pZuXian] = qZuXian;
				size--;
			}
		}
	}
}
