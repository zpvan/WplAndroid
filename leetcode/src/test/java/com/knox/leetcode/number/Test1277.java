package com.knox.leetcode.number;

import java.util.Arrays;

class Test1277 {
  public int countSquares(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dp[i][j] = matrix[i][j];
      }
    }
    print(dp);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (dp[i][j] == 0) {
          continue;
        }
        if (i > 0 && j > 0) {
          if (dp[i - 1][j - 1] > 0 && dp[i - 1][j] > 0 && dp[i][j - 1] > 0) {
            dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
          }
        }
      }
    }
    print(dp);

    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        count += dp[i][j];
      }
    }

    return count;
  }

  private void print(int[][] dp) {
    Arrays.stream(dp)
        .forEach(
            line -> {
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < line.length; i++) {
                sb.append(line[i] + " ");
              }
              System.out.println(sb);
            });
    System.out.println("");
  }
}
