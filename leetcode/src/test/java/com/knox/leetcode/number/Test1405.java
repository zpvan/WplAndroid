package com.knox.leetcode.number;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Test1405 {

	public String longestDiverseString(int a, int b, int c) {
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1, o2) -> {
			return o2[0] - o1[0];
		});
		if (a > 0) queue.add(new int[]{a, 'a'});
		if (b > 0) queue.add(new int[]{b, 'b'});
		if (c > 0) queue.add(new int[]{c, 'c'});
		StringBuilder ans = new StringBuilder();
		while (!queue.isEmpty()) {
			int[] biggest = queue.poll();
			int biggest_0 = biggest[0];
			int biggest_min = Math.min(biggest_0, 2);
			IntStream.range(0, biggest_min).forEach(i -> ans.append((char) biggest[1]));
			if (queue.isEmpty()) break;
			int[] bigger = queue.poll();
			int bigger_0 = bigger[0];
			int shouldInsert = 1;
			if (bigger_0 == biggest_0) {
				shouldInsert = 2;
			}
			int bigger_min = Math.min(bigger_0, shouldInsert);
			IntStream.range(0, bigger_min).forEach(i -> ans.append((char) bigger[1]));
			if (biggest_0 > 2) {
				biggest[0] = biggest_0 - 2;
				queue.add(biggest);
			}
			if (bigger_0 > shouldInsert) {
				bigger[0] = bigger_0 - shouldInsert;
				queue.add(bigger);
			}
		}
		return ans.toString();
	}
}
