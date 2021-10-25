package com.knox.leetcode.number;

public class Test787 {


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // dp
        /**
         * 状态转移方程
         * dp[t][i] 表示坐t次航班, 到达i地的最小花费
         * dp[t][i] = dp[t-1][j] + cost(j,i)
         * 当t=0时, dp[0][i] 如果 i==src,则dp[0][i]=0
         *                  如果 i!=src,则dp[0][i]=infinite
         */
        long[][] dp = new long[k + 2][n]; // k次,n地

        // 初始化
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][src] = 0;

        for (int i = 1; i <= k + 1; i++) {
            for (int[] flight : flights) {
                int s0 = flight[0], t0 = flight[1], cost = flight[2];
                dp[i][t0] = Math.min(dp[i - 1][s0] + cost, dp[i][t0]);
            }
        }

        long ans = Integer.MAX_VALUE;
        for (int i = 1; i <= k + 1; i++) {
            ans = Math.min(ans, dp[i][dst]);
        }
        return ans == Integer.MAX_VALUE ? -1 : (int) ans;
    }
}
