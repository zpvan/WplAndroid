package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Stack;

public class Test844 {

	@Test
	public boolean backspaceCompare(String s, String t) {
		Stack<Character> ss = new Stack<>();
		Stack<Character> ts = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '#') {
				if (!ss.empty()) {
					ss.pop();
				}
			} else {
				ss.push(s.charAt(i));
			}
		}

		for (int i = 0; i < t.length(); i++) {
			if (t.charAt(i) == '#') {
				if (!ts.empty()) {
					ts.pop();
				}
			} else {
				ts.push(t.charAt(i));
			}
		}

		while (!ss.empty()) {
			if (ts.empty()) {
				return false;
			}
			if (ss.pop().charValue() != ts.pop().charValue()) {
				return false;
			}
		}

		return ts.empty();
	}
}
