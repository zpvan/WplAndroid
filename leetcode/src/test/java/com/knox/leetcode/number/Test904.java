package com.knox.leetcode.number;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test904 {

	@Test
	public void test() {
		System.err.println("ans: " + totalFruit(new int[]{0, 1, 2, 2}));
	}

	public int totalFruit(int[] fruits) {
		int ans = 0;
		Set<Integer> set = new HashSet<>();
		int mark = 0;
		int curr = 0;
		for (int i = 0; i < fruits.length; i++) {
			System.out.println("i = " + i + ", curr = " + curr);
			if ((set.size() == 2) && (!set.contains(fruits[i]))) {
				ans = Math.max(ans, curr);
				i = mark - 1;
				set.clear();
				curr = 0;
				continue;
			}
			if ((set.size() == 1) && (!set.contains(fruits[i]))) {
				mark = i;
			}
			curr++;
			set.add(fruits[i]);
		}

		ans = Math.max(ans, curr);

		return ans;
	}
}
