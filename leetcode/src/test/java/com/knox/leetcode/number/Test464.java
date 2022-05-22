package com.knox.leetcode.number;

import org.junit.Test;

public class Test464 {

	@Test
	public void test() {
		System.err.println("ans: " + canIWin(10, 11));
		System.err.println("ans: " + canIWin(10, 0));
		System.err.println("ans: " + canIWin(10, 1));
	}

	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// dfs / dp
		// 先判断双方都失败的情况
		if (((1 + maxChoosableInteger) * maxChoosableInteger / 2) < desiredTotal) return false;
		// 总有一方会赢
		/**
		 * 因为 0 < maxChoosableInteger <= 20
		 * 用 int usedNumber 来记录被使用过的数字
         *
         * currentTotal = 0 开始, 说明是先手方
         *
         * dp 记录状态, 记录 dp[usedNumber] = 1(赢) / 0(未知) / -1(输)
         *
         * boolean 类型返回值, 说明能否赢
         *
         * 最后, 开始暴搜
		 */
		return dfs(maxChoosableInteger, desiredTotal, 0, 0,
                new int[1 << maxChoosableInteger]);
	}

	private boolean dfs(int maxChoosableInteger, int desiredTotal,
						int usedNumber, int currentTotal, int[] dp) {
        // 递归结束条件
        if (dp[usedNumber] != 0) {
            return dp[usedNumber] == 1;
        }

        // 暴力搜索
        for (int i = 0; i < maxChoosableInteger; i++) {
            if (((usedNumber >> i) & 1) == 1) continue; // 使用过该数字了
            if ((i + 1 + currentTotal >= desiredTotal)
                    || (!dfs(maxChoosableInteger, desiredTotal,
                    usedNumber | (1 << i), currentTotal + i + 1, dp))) {
                dp[usedNumber] = 1;
                return true;
            }
        }

        dp[usedNumber] = -1;
        return false;
	}
}
