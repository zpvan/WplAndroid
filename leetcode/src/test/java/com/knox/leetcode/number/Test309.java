package com.knox.leetcode.number;

import org.junit.Test;

public class Test309 {

  @Test
  public void test() {
    int[] ps = new int[] {1, 2, 3, 0, 2};
    System.out.println("max: " + maxProfit(ps));
  }

  public int maxProfit(int[] prices) {
    /**
     * 给定一个整数数组，其中第i个元素代表了第i天的股票价格 。
     *
     * 设计一个算法计算出最大利润。
     * 在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     */

    /**
     * 第i天结束后，有三种状态：其中处于冷冻期意思是，第i天结束后冷冻，即i+1天不能买入
     * 目前拥有一支股票：对应的「累计最大收益」记为f[i][0]
     * 目前没有股票，处于冷冻期：对应的「累计最大收益」记为f[i][1]
     * 目前没有股票，处于非冷冻期：对应的「累计最大收益」记为f[i][2]
     *
     * 换言之：
     * 在第i天时，可以不违反规则的前提下进行「买入」和「卖出」操作，此时第i天的状态从i-1天状态转移而来；
     * 我们也可以不进行任何操作，此时第i天的状态就等同于第i-1天的状态。分别对3种状态分析：
     *
     * 1. 对于f[i][0]，也就是第i天后持有一支股票。
     * 可以是第i天买入(f[i-1][2] - prices[i])，
     * 也可以是第i-1天已经持有(f[i-1][0])。
     * f[i][0] = max(f[i-1][0], f[i-1][2] - prices[i]);
     *
     * 2. 对于f[i][1]，也就是第i天后不持有股票，且处于冷冻期。
     * 换言之，第i天卖出股票，也就是i-1天时持有股票
     * f[i][1] = f[i-1][0] + prices[i]
     *
     * 3. 对于f[i][2]，也就是第i天后不持有股票，且不处于冷冻期。
     * 换言之，第i-1天不持有股票
     * f[i][2] = max(f[i-1][1], f[i-1][2])
     *
     * ans = max(f[i][0], f[i][1], f[i][2]) => max(f[i][1], f[i][2])
     *
     * 初始化状态
     * f[0][0] = -prices[0]
     * f[0][1] = 0
     * f[0][2] = 0
     */

    if (prices.length == 0) {
      return 0;
    }
    int[][] dp = new int[prices.length][3];

    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    dp[0][2] = 0;

    for (int i = 1; i < prices.length; i++) {
      // max(f[i-1][0], f[i-1][2] - prices[i]);
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
      // f[i][1] = f[i-1][0] + prices[i]
      dp[i][1] = dp[i - 1][0] + prices[i];
      // f[i][2] = max(f[i-1][1], f[i-1][2])
      dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
    }

    // ans = max(f[i][0], f[i][1], f[i][2]) => max(f[i][1], f[i][2])
    return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
  }
}
