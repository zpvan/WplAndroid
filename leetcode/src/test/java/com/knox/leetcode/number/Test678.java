package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Stack;

public class Test678 {
    
	@Test
	public void test() {
		String input = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
		System.err.println("input: " + input + ", output: " + checkValidString(input));
	}

	public boolean checkValidString(String s) {
		Stack<Integer> left = new Stack<>();
		Stack<Integer> star = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ')') {
				if (left.isEmpty()) {
					if (star.isEmpty()) return false;
					star.pop();
				} else {
					left.pop();
				}
			} else if (c == '*') star.push(i);
			else left.push(i);
		}
		while (!left.isEmpty()) {
			if (star.isEmpty()) return false;
			if (star.pop() < left.pop()) return false;
		}
		return true;
	}
}
