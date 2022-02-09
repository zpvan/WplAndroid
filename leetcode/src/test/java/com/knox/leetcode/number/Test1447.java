package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.List;

public class Test1447 {

	/**
	 * fraction = numerator / denominator
	 *
	 * @param n
	 * @return
	 */
	public List<String> simplifiedFractions(int n) {
		List<String> ans = new ArrayList<>();
		for (int denominator = 2; denominator <= n; denominator++) {
			for (int numerator = 1; numerator < denominator; numerator++) {
				if (gcm(denominator, numerator) == 1) ans.add(numerator + "/" + denominator);
			}
		}
		return ans;
	}

	private int gcm(int a, int b) {
		return b == 0 ? a : gcm(b, a % b);
	}
}
