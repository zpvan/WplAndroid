package com.knox.leetcode.number;

public class Test357 {

	public int countNumbersWithUniqueDigits(int n) {
		if (n == 0) return 1;
		int ans = 10;
		for (int i = 2, last = 9; i <= n; i++) {
			int cur = last * (10 - i + 1);
			ans += cur;
			last = cur;
		}
		return ans;
	}
}
