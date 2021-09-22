package com.knox.leetcode.number;

class Test63 {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    if (obstacleGrid[m - 1][n - 1] == 1) {
      return 0;
    }
    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          dp[i][j] = 0;
          continue;
        }
        if (i - 1 < 0) {
          dp[i][j] = dp[i][j - 1];
          continue;
        }
        if (j - 1 < 0) {
          dp[i][j] = dp[i - 1][j];
          continue;
        }
        if (obstacleGrid[i - 1][j] == 1) {
          dp[i - 1][j] = 0;
        }
        if (obstacleGrid[i][j - 1] == 1) {
          dp[i][j - 1] = 0;
        }
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[m - 1][n - 1];
  }
}
