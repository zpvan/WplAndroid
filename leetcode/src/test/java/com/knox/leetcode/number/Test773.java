package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Test773 {

	@Test
	public void test() {

	}

	List<Integer>[] reachable = new ArrayList[6];

	/**
	 *
	 * @param board
	 * @return
	 */
	public int slidingPuzzle(int[][] board) {
		initReachable();

		// BFS
		
	}

	private void initReachable() {
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
		IntStream.range(0, 6).forEach(i -> {
			List<Integer> list = new ArrayList<>();
			if (i == 0) {
				list.add(1);
				list.add(3);
			} else if (i == 1) {
				list.add(0);
				list.add(2);
				list.add(4);
			} else if (i == 2) {
				list.add(1);
				list.add(5);
			} else if (i == 3) {
				list.add(0);
				list.add(4);
			} else if (i == 4) {
				list.add(1);
				list.add(3);
				list.add(5);
			} else {
				// i == 5
				list.add(2);
				list.add(4);
			}
			reachable[i] = list;
		});
	}
}
