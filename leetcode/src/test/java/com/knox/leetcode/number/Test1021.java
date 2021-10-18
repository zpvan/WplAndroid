package com.knox.leetcode.number;

import org.junit.Test;

public class Test1021 {

    @Test
    public void test() {
        String input = "(()())(())";
        System.err.println("input: " + input + ", output: " + removeOuterParentheses(input));
    }

    public String removeOuterParentheses(String s) {
        int left = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (left != 0) sb.append('(');
                left++;
            } else {
                left--;
                if (left != 0) sb.append(')');
            }
        }
        return sb.toString();
    }
}
