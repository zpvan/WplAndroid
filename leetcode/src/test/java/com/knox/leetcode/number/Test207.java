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
		topoSortedByKahn(numCourses, prerequisites, g);


	}

	private void topoSortedByKahn(int v, int[][] prerequisites, Graph g) {
		// 统计每个顶点的入度
		int[] inDegree = new int[v];
		for (int i = 0; i < v; i++) {
			
		}
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
