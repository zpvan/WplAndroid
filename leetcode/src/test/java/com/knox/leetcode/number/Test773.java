package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Test773 {

	/**
	 * [0+0, 0+1, 0+2]
	 * [3+0, 3+1, 3+2]
	 * ==>
	 * reachable[0+0] = {0+1, 3+0}      = {1, 3}
	 * reachable[0+1] = {0+0, 0+2, 3+1} = {0, 2, 4}
	 * reachable[0+2] = {0+1, 3+2}      = {1, 5}
	 * reachable[3+0] = {0+0, 3+1}      = {0, 4}
	 * reachable[3+1] = {0+1, 3+0, 3+2} = {1, 3, 5}
	 * reachable[3+2] = {0+2, 3+1}      = {2, 4}
	 */
	int[][] reachable = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

	@Test
	public void test() {

	}

	private List<String> moveOneStep(String curr) {
		int zeroPos = curr.indexOf("0");
		List<String> ans = new ArrayList<>();
		char[] charArray = curr.toCharArray();
		for (int otherPos : reachable[zeroPos]) {
			swap(charArray, zeroPos, otherPos);
			ans.add(new String(charArray));
			swap(charArray, zeroPos, otherPos);
		}
		return ans;
	}

	private void swap(char[] curr, int zeroPos, int otherPos) {
		char temp = curr[zeroPos];
		curr[zeroPos] = curr[otherPos];
		curr[otherPos] = temp;
	}

	/**
	 * @param board
	 * @return
	 */
	public int slidingPuzzle(int[][] board) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(board[i][j]);
			}
		}
		String sBoard = sb.toString();
		if ("123450".equals(sBoard)) return 0;
		// BFS
		int step = 0;
		Queue<String> queue = new LinkedList<>();
		queue.offer(sBoard);
		Set<String> visited = new HashSet<>();
		visited.add(sBoard);

		while (!queue.isEmpty()) {
			++step;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String curr = queue.poll();
				for (String next : moveOneStep(curr)) {
					if (visited.contains(next)) continue;
					if ("123450".equals(next)) return step;
					queue.add(next);
					visited.add(next);
				}
			}
		}
		return -1;
	}
}
