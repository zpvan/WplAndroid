package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Test735 {

	@Test
	public void test() {
		System.out.println("ans: " + Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
		System.out.println("ans: " + Arrays.toString(asteroidCollision(new int[]{8, -8})));
		System.out.println("ans: " + Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
		System.out.println("ans: " + Arrays.toString(asteroidCollision(new int[]{-2, -1, 1, 2})));
	}

	public int[] asteroidCollision(int[] asteroids) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int asteroid : asteroids) {
			list.add(asteroid);
		}

		boolean conflict = true;
		while (conflict && !list.isEmpty()) {
			conflict = false;
			boolean direction = list.peek() > 0;
			int size = list.size();
			for (int i = 1; i < size; i++) {
				int next = list.get(i);
				boolean next_d = next > 0;
				if (direction != next_d) {
					conflict = true;
					int prev = list.get(i - 1);
					if (Math.abs(next) == Math.abs(prev)) {
						System.out.println("remove1: " + prev);
						System.out.println("remove1: " + next);
						list.remove(i - 1);
						list.remove(i - 1);
					} else if (Math.abs(next) > Math.abs(prev)) {
						System.out.println("remove2: " + prev);
						list.remove(i - 1);
					} else {
						System.out.println("remove3: " + next);
						list.remove(i);
					}
					break;
				}
			}
		}

		int[] ans = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ans[i] = list.get(i);
		}
		return ans;
	}

	@Test
	public void test2() {
		System.out.println("ans: " + Arrays.toString(asteroidCollision2(new int[]{5, 10, -5})));
		System.out.println("ans: " + Arrays.toString(asteroidCollision2(new int[]{8, -8})));
		System.out.println("ans: " + Arrays.toString(asteroidCollision2(new int[]{10, 2, -5})));
		System.out.println("ans: " + Arrays.toString(asteroidCollision2(new int[]{-2, -1, 1, 2})));
	}

	public int[] asteroidCollision2(int[] asteroids) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		for (int aster : asteroids) {
			boolean alive = true;
			while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
				alive = stack.peek() < -aster; // aster 是否存在
				if (stack.peek() <= -aster) {  // 栈顶行星爆炸
					stack.pop();
				}
			}
			if (alive) {
				stack.push(aster);
			}
		}
		int size = stack.size();
		int[] ans = new int[size];
		for (int i = size - 1; i >= 0; i--) {
			ans[i] = stack.pop();
		}
		return ans;
	}
}
