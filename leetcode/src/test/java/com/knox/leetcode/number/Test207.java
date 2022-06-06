package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Test207 {

	List<List<Integer>> prerequisitesList;
	/**
	 * 0 -> 未搜索
	 * 1 -> 正搜索
	 * 2 -> 已完成
	 */
	int[] visited;

	@Test
	public void test() {
		System.err.println("ans: " + canFinish(2, new int[][]{{0, 1}}));
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		prerequisitesList = new ArrayList<>(numCourses);
		IntStream.range(0, numCourses).forEach(i -> prerequisitesList.add(new ArrayList<>()));
		visited = new int[numCourses];

		for (int[] ele : prerequisites) {
			prerequisitesList.get(ele[0]).add(ele[1]);
		}

		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {
				if (!_dfs(i)) return false;
			}
		}
		return true;
	}

	private boolean _dfs(int u) {
		visited[u] = 1; // 正搜索
		for (int prefix : prerequisitesList.get(u)) {
			if (visited[prefix] == 0) {
				if (!_dfs(prefix)) return false;
			} else if (visited[prefix] == 1) {
				return false;
			}
		}
		visited[u] = 2; // 已完成
		return true;
	}

	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		// 拓扑排序
		// 建图
		Graph g = new Graph(numCourses);
		for (int[] p : prerequisites) {
			g.addEdge(p[0], p[1]);
		}

		// Kahn 算法
		return topoSortedByKahn(g);
	}

	private boolean topoSortedByKahn(Graph g) {
		// 统计每个顶点的入度
		int[] inDegree = new int[g.v];
		for (int i = 0; i < g.v; i++) {
			for (int j = 0; j < g.adj[i].size(); ++j) {
				int w = g.adj[i].get(j); // i->w
				inDegree[w]++;
			}
		}
		boolean[] visited = new boolean[g.v];
		// bfs
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < g.v; ++i) {
			if (inDegree[i] == 0)
				queue.add(i);
		}
		while (!queue.isEmpty()) {
			int i = queue.remove();
			visited[i] = true;
			for (int j = 0; j < g.adj[i].size(); ++j) {
				int k = g.adj[i].get(j);
				inDegree[k]--;
				if (inDegree[k] == 0)
					queue.add(k);
			}
		}
		for (int i = 0; i < g.v; i++) {
			if (!visited[i]) return false;
		}
		return true;
	}

	private class Graph {
		private int v; // 顶点个数
		private List<Integer> adj[]; // 邻接表

		public Graph(int v) {
			this.v = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; i++) {
				adj[i] = new LinkedList<>();
			}
		}

		public void addEdge(int s, int t) { // s 先于 t, 边 s -> t
			adj[s].add(t);
		}
	}
}
