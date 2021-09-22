package com.knox.leetcode.offer;

import java.util.Arrays;

class Test040 {
  public int maximalRectangle(String[] matrix) {
    int m = matrix.length;
    int n = matrix[0].length();
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dp[i][j] = matrix[i].charAt(j) == '0' ? 0 : 10000;
      }
    }
    Arrays.stream(dp)
        .forEach(
            line -> {
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
              }
              System.out.println(sb);
            });

    return 0;
  }
}
