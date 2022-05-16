package com.knox.leetcode.offer;

import org.junit.Test;

public class Test0105 {

	@Test
	public void test() {
		/**
		 * first = "pale"
		 * second = "ple"
		 * 输出: True
		 *
		 * first = "pales"
		 * second = "pal"
		 * 输出: False
		 */
		System.err.println("ans: " + oneEditAway("pale", "ple"));
		System.err.println("ans: " + oneEditAway("pales", "pal"));
	}

	public boolean oneEditAway(String first, String second) {
		char[] shorter, longer;
		int err = 0;
		if (first.length() == second.length()) {
			shorter = first.toCharArray();
			longer = second.toCharArray();
			for (int i = 0; i < shorter.length; i++) {
				if (shorter[i] != longer[i] && ((err++) > 1)) {
					return false;
				}
			}
			return true;
		} else if (first.length() < second.length()) {
			shorter = first.toCharArray();
			longer = second.toCharArray();
		} else {
			shorter = second.toCharArray();
			longer = first.toCharArray();
		}
		if (longer.length - shorter.length > 1) return false;
		if (shorter.length == 0) return true;
		for (int i = 0; i < shorter.length; i++) {
			if (shorter[i] != longer[i + err]) {
				err++;
				i--;
			}
			if (err > 1) {
				return false;
			}
		}
		return true;
	}

	public boolean oneEditAway2(String first, String second) {
		int n = first.length(), m = second.length(); //获取字符串长度
		if(Math.abs(m - n) > 1) return false; // 长度相差大于1一定不能通过一次编辑完成

		int diff = 0; //两个字符串的不同数

		for(int i = 0, j = 0; i < n && j < m; i++, j++) {
			if(first.charAt(i) != second.charAt(j)) {
				// 找到不同处 diff + 1
				diff++;

				if(n > m) j--; // 如果 n > m说明只能通过在second插入来完成一次编辑
				else if(n < m) i--; // 和上面同理只能在first中插入一次来完成一次编辑

			}
		}
		return diff < 2; // 如果差异数小于2能完成一次编辑
	}
}
