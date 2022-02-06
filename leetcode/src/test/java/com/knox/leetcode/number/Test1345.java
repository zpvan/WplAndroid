package com.knox.leetcode.number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1345 {

	// BFS
	int INF = 0x3f3f3f3f;

	public int minJumps(int[] arr) {
		int n = arr.length;
		// 同一个值的所有下标存到一个List中
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = n - 1; i >= 0; i--) {
			// 倒序插入，下标越大，优先出队
			List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
			list.add(i);
			map.put(arr[i], list);
		}
		int[] steps = new int[n];
		Arrays.fill(steps, INF);
		Deque<Integer> queue = new ArrayDeque<>();
		queue.addLast(0); // 从下标0开始
		steps[0] = 0; // 下标0只需0步即可访问
		// BFS常规做法
		while (!queue.isEmpty()) {
			int poll = queue.pollFirst(), step = steps[poll];
			if (poll == n - 1) return step;
			if (poll + 1 < n && steps[poll + 1] == INF) {
				// 向前跳
				steps[poll + 1] = step + 1;
				queue.addLast(poll + 1);
			}
			if (poll - 1 >= 0 && steps[poll - 1] == INF) {
				// 向后跳
				steps[poll - 1] = step + 1;
				queue.addLast(poll - 1);
			}
			// 等值跳
			List<Integer> list = map.getOrDefault(arr[poll], new ArrayList<>());
			for (int e : list) {
				if (steps[e] == INF) {
					steps[e] = step + 1;
					queue.addLast(e);
				}
			}
			map.remove(arr[poll]);
		}
		return -1;
	}
}
