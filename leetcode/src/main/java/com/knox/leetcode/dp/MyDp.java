package com.knox.leetcode.dp;

public class MyDp {

    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length == 0) {
            return "";
        }

        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        int begin = 0;
        int maxLength = 1;
        char[] ca = s.toCharArray();
        for (int l = 2; l <= length; l++) {
            for (int i = 0; i < length; i++) {
                int j = i + l - 1;
                if (j >= length) {
                    break;
                }
                if (ca[i] != ca[j]) {
                    dp[i][j] = false;
                } else {
                    // ca[i] == ca[j]
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && l > maxLength) {
                    maxLength = l;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLength);
    }
}
