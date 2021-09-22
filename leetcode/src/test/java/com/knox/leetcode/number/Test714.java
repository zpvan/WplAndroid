package com.knox.leetcode.number;

class Test714 {

    public int maxProfit(int[] prices, int fee) {
        /**
         *
         * 卖掉时才收手续费
         *
         * dp[i][0]表示第i天后，持有股票时的现金
         * max(dp[i-1][0], (dp[i - 1][1] - prices[i]))
         *
         * dp[i][1]表示第i天后，不持有股票时的现金
         * max((dp[i-1][0] + prices[i] - fee), dp[i-1][1])
         *
         * 初始化
         * dp[0][0] = -prices[0];
         * dp[0][1] = 0;
         *
         * 结果
         * ans = dp[n][1]
         */
        if (prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], (dp[i-1][1] - prices[i]));
            dp[i][1] = Math.max((dp[i-1][0] + prices[i] - fee), dp[i-1][1]);
        }

        return dp[prices.length - 1][1];
    }
}
