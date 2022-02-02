package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
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
}
