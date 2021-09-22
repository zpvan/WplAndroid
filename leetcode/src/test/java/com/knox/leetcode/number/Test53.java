package com.knox.leetcode.number;

public class Test53 {

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        int ans = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + Math.max(0, dp[i - 1]);
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }

        return ans;
    }
}
