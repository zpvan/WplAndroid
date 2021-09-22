package com.knox.leetcode.number;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Test542 {
  public int[][] updateMatrix(int[][] mat) {
    int[][] ans;
    // ans = bfsMethod(mat);
    ans = dpMethod(mat);
    return ans;
  }

  private int[][] dpMethod(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dp[i][j] = mat[i][j] == 0 ? 0 : 10000;
      }
    }
    print(dp);
    // 左上角开始
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i - 1 >= 0) {
          // 上面有点
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
        }
        if (j - 1 >= 0) {
          // 左边有点
          dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
        }
      }
    }
    print(dp);
    // 右下角开始
    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        if (i + 1 < m) {
          // 下面有点
          dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
        }
        if (j + 1 < n) {
          // 右边有点
          dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
        }
      }
    }
    print(dp);

    return new int[1][1];
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

  private int[][] bfsMethod(int[][] mat) {
    int n = mat.length;
    int m = mat[0].length;

    int[][] ans = new int[n][m];
    int[][] wsad = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    Deque<int[]> queue = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 0) {
          queue.addLast(new int[] {i, j, mat[i][j]});
          mat[i][j] = -1;
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] pos = queue.removeFirst();
      int x = pos[0];
      int y = pos[1];
      int value = pos[2];
      for (int i = 0; i < wsad.length; i++) {
        int nx = x + wsad[i][0];
        int ny = y + wsad[i][1];
        if (mat[nx][ny] != -1 && nx >= 0 && nx < n && ny >= 0 && ny < m) {
          ans[nx][ny] = value + 1;
          queue.addLast(new int[] {nx, ny, mat[nx][ny]});
          mat[nx][ny] = -1;
        }
      }
    }

    return ans;
  }
}
