package com.knox.leetcode.number;

import org.junit.Test;

public class Test9 {

	@Test
	public void test() {
		int x = 121;
		System.out.println(x + "=" + isPalindrome(x));
	}

	public boolean isPalindrome(int x) {
		if (x < 0) return false;
		String str = String.valueOf(x);
		int left = 0, right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) return false;
			left++;
			right--;
		}
		return true;
	}
}
