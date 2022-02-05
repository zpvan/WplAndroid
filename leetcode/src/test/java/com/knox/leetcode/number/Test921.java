package com.knox.leetcode.number;

import org.junit.Test;

public class Test921 {

	@Test
	public void test() {
		String input = "()))((";
		System.err.println("input: " + input + ", output: " + minAddToMakeValid(input));
	}

	public int minAddToMakeValid(String s) {
		int left = 0, err = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ')') {
				if (left == 0) err++;
				else left--;
			} else left++;
		}
		return err + left;
	}
}
