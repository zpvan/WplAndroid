package com.knox.leetcode.number;

import java.util.Arrays;

class Test846 {

	public boolean isNStraightHand(int[] hand, int groupSize) {
		if (hand.length % groupSize != 0) return false;
		Arrays.sort(hand);
		boolean[] visited = new boolean[hand.length];
		int min = 0;
		while ((min = findMinFalse(visited, min)) < visited.length) {
			int remain = groupSize;
			int last = -1;
			for (int i = 0; i < hand.length && (remain > 0); i++) {
				if (visited[i]) continue;
				if (last != -1) {
					if (hand[i] - last == 0) continue;
					if (hand[i] - last > 1) return false;
				}
				// hand[i] - last == 1
				last = hand[i];
				visited[i] = true;
				remain--;
			}
			if (remain > 0) return false;
		}
		return true;
	}

	private int findMinFalse(boolean[] visited, int min) {
		for (int i = min; i < visited.length; i++) {
			if (!visited[i]) return i;
		}
		return visited.length;
	}
}
