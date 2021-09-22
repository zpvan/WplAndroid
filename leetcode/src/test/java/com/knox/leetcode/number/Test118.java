package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Test118 {
    public List<List<Integer>> generate(int numRows) {
        int[][] dp = new int[numRows][numRows];

        dp[0][0] = 1;

        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    dp[i][j]= 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> line = new ArrayList<>();
            Arrays.stream(dp[i]).limit(i + 1).forEach(it -> line.add(it));
            ans.add(line);
        }

        return ans;
    }
}
