package com.knox.leetcode.number;

class Test121 {
	class Solution {
		public int maxProfit(int[] prices) {
			int sum = 0;
			int max = 0;
			for (int i = 1; i < prices.length; i++) {
				sum += prices[i] - prices[i - 1];
				max = Math.max(sum, max);
				sum = Math.max(sum, 0);
			}
			return sum;
		}
	}
}
