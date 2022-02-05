package com.knox.leetcode.number;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Test859 {

	@Test
	public void test() {
		String a = "aaaaaaabc";
		String b = "aaaaaaacb";
		System.err.println("buddyStrings ans: " + buddyStrings(a, b));
	}

	public boolean buddyStrings(String s, String goal) {
		if (s.length() != goal.length()) return false;
		int l = -1, r = -1;
		Set<Character> set = new HashSet<>();
		boolean duplicate = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != goal.charAt(i)) {
				if (l == -1) l = i;
				else if (r == -1) r = i;
				else return false;
			}
			if (set.contains(s.charAt(i))) duplicate = true;
			set.add(s.charAt(i));
		}
		if (l == -1) return duplicate;
		if (r == -1) return false;
		return s.charAt(l) == goal.charAt(r) && s.charAt(r) == goal.charAt(l);
	}
}
