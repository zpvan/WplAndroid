package com.knox.leetcode.number;

import java.util.Arrays;
import java.util.Stack;

class Test150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Arrays.stream(tokens).forEach(token -> {
            if (digital(token)) {
                stack.push(Integer.valueOf(token));
            } else {
                int second = stack.pop();
                int first = stack.pop();
                int result = 0;
                if (token.equals("-")) {
                    result = first - second;
                }
                if (token.equals("+")) {
                    result = first + second;
                }
                if (token.equals("/")) {
                    result = first / second;
                }
                if (token.equals("*")) {
                    result = first * second;
                }
                stack.push(result);
            }
        });
        return stack.pop();
    }

    private boolean digital(String token) {
        try {
            Integer.valueOf(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
