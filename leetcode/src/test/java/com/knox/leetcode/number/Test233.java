package com.knox.leetcode.number;

import org.junit.Test;

public class Test233 {

	@Test
	public void test() {
    	System.err.println("ans: " + maxPower("abbcccddddeeeeedcba"));
	}

	public int maxPower(String s) {
		int ans = 0, max = 0;
		char cur = '_';
		for (char c : s.toCharArray()) {
			ans = cur != c ? 1 : (++ans);
			cur = c;
			max = Math.max(ans, max);
		}
		return max;
	}
}
