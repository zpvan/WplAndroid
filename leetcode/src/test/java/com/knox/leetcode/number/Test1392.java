package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test1392 {

	@Test
	public void test() {
		System.out.println(longestPrefix("bba"));
	}

	public String longestPrefix(String s) {
		// KMP算法 的 next 数组
		int[] nexts = getNexts(s, s.length());
		Arrays.stream(nexts).forEach(System.err::println);
		//int max = Arrays.stream(nexts).max().orElse(-1);
		int max = nexts[nexts.length - 1];
		return max == -1 ? "" : s.substring(0, max + 1);
	}

	private int[] getNexts(String b, int m) {
		int[] next = new int[m];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < m; ++i) {
			while ((k != -1) && (b.charAt(k + 1) != b.charAt(i))) {
				k = next[k];
			}
			if (b.charAt(k + 1) == b.charAt(i)) {
				++k;
			}
			next[i] = k;
		}
		return next;
	}
}
