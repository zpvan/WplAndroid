package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Stack;

public class Test856 {

	@Test
	public void test() {
		String input = "(()(()))";
		System.err.println("input: " + input + ", output: " + scoreOfParentheses(input));
	}

	public int scoreOfParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ')') {
				int A = stack.pop();
				if (A == -1) A = 1;
				else {
					A *= 2;
					stack.pop();
				}
				if (!stack.isEmpty() && stack.peek() != -1) stack.push(A + stack.pop());
				else stack.push(A);
			} else stack.push(-1);
		}
		return (stack.isEmpty()) ? 0 : stack.pop();
	}
}
