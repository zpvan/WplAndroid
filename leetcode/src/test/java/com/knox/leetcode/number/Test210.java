package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Test210 {

	@Test
	public void test() {

	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] ans = new int[numCourses];
		int ans_index = 0;
		// prepare
		int[] indegs = new int[numCourses];
		List<List<Integer>> edges = new ArrayList<>();
		IntStream.range(0, numCourses).forEach(i -> edges.add(new ArrayList<>()));
		for (int[] ele : prerequisites) {
			edges.get(ele[1]).add(ele[0]);
			indegs[ele[0]]++;
		}

		// bfs
		Queue<Integer> queue = new LinkedList<>();

		// init
		for (int i = 0; i < numCourses; i++) {
			if (indegs[i] == 0) queue.offer(i);
		}

		while (!queue.isEmpty()) {
			int u = queue.poll();
			ans[ans_index++] = u;

			for (int v : edges.get(u)) {
				indegs[v]--;
				if (indegs[v] == 0) queue.offer(v);
			}
		}

		return ans_index == numCourses ? ans : new int[0];
	}
}
