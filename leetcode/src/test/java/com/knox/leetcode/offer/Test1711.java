package com.knox.leetcode.offer;

import java.util.PriorityQueue;

class Test1711 {

	public int findClosest(String[] words, String word1, String word2) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
			return a[1] - b[1];
		});
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(word1)) {
				queue.add(new int[]{1, i});
			} else if (words[i].equals(word2)) {
				queue.add(new int[]{2, i});
			}
		}
		int ans = 100000;
		int[] now = null;
		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			if (now == null) {
				now = poll;
			} else {
				int t1 = now[0], t2 = poll[0];
				int p1 = now[1], p2 = poll[1];
				if (t1 != t2) {
					ans = Math.min(ans, p2 - p1);
				}
				now = poll;
			}
		}
		return ans;
	}
}
