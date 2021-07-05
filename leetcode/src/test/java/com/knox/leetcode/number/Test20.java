package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Stack;

public class Test20 {

    @Test
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.empty()) {
                return false;
            }
            Character pop = stack.pop();
            if ((c == ')' && pop != '(') || (c == '}' && pop != '{') || (c == ']' && pop != '[')) {
                return false;
            }
        }
        return stack.empty();
    }
}
