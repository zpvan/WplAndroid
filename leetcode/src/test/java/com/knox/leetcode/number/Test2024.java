package com.knox.leetcode.number;

import org.junit.Test;

public class Test2024 {

	@Test
	public void test() {
		/**
		 * 输入：answerKey = "TTFF", k = 2
		 * 输出：4
		 *
		 * 输入：answerKey = "TFFT", k = 1
		 * 输出：3
		 *
		 * 输入：answerKey = "TTFTTFTT", k = 1
		 * 输出：5
		 */
		System.out.println("ans1: " + maxConsecutiveAnswers("TTFF", 2));
		System.out.println("ans2: " + maxConsecutiveAnswers("TFFT", 1));
		System.out.println("ans3: " + maxConsecutiveAnswers("TTFTTFTT", 1));
	}

	public int maxConsecutiveAnswers(String answerKey, int k) {
		return Math.max(cal(answerKey, k, 'T'), cal(answerKey, k, 'F'));
	}

	public int cal(String answerKey, int k, char w) {
		char[] chars = answerKey.toCharArray();
		int ans = 0;
		for (int l = 0, r = 0, c = 0; r < chars.length; r++) {
			c += chars[r] == w ? 0 : 1;
			while (c > k) {
				c -= chars[l++] == w ? 0 : 1;
			}
			ans = Math.max(ans, r - l + 1);
		}
		return ans;
	}

	/**
	 * sliding-window 滑动窗口
	 * 一般有会有左右指针来控制窗口，窗口是"某种维度"的固定长度，譬如l到r之间"1"的个数固定
	 */
	public int cal2(String answerKey, int k, char w) {
		char[] chars = answerKey.toCharArray();
		int ans = 0;
		for (int l = 0, r = 0, c = 0; r < chars.length; r++) {
			c += chars[r] == w ? 0 : 1;
			while (c > k) {
				c -= chars[l++] == w ? 0 : 1;
			}
			ans = Math.max(ans, r - l + 1);
		}
		return ans;
	}
}
