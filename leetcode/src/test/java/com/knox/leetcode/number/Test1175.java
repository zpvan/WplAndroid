package com.knox.leetcode.number;

import org.junit.Test;

public class Test1175 {

	int MOD = (int) (1e9 + 7);

	@Test
	public void test() {
		System.out.println("ans: " + numPrimeArrangements(5));
		System.out.println("ans: " + numPrimeArrangements(100));
	}

	public int numPrimeArrangements(int n) {
		// 找到质数的个数
		int pn = 0;
		for (int i = 1; i <= n; i++) {
			pn += isPrime(i) ? 1 : 0;
		}

		// 则非质数的个数
		int npn = n - pn;

		// pn! * npn! 等于方案总数
		long a = factorial(pn);
		long b = factorial(npn);
		return (int) (a * b % MOD);
	}

	// 阶乘
	private long factorial(int n) {
		long ans = 1;
		for (int i = 1; i <= n; i++) {
			ans = ans * i % MOD;
		}
		return ans;
	}

	// 判断是否是质数
	private boolean isPrime(int n) {
		if (n <= 1) return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}
}
