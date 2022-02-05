package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Stack;

public class Test682 {

	@Test
	public int calPoints(String[] ops) {
		Stack<Integer> stack = new Stack<>();
		for (String op : ops) {
			try {
				if ("+".equals(op)) {
					int first = stack.pop();
					int second = 0;
					if (!stack.empty()) {
						second = stack.peek();
						stack.push(first);
					}
					stack.push(first + second);
				} else if ("D".equals(op)) {
					stack.push(2 * stack.peek());
				} else if ("C".equals(op)) {
					stack.pop();
				} else {
					stack.push(Integer.valueOf(op));
				}
			} catch (Exception e) {
				continue;
			}
		}
		return stack.stream().reduce(Integer::sum).orElse(0);
	}
}
