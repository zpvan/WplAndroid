package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test6 {

	@Test
	public void test() {
		System.out.println("ans: " + convert("PAYPALISHIRING", 3));
		System.out.println("ans: " + convert("PAYPALISHIRING", 4));
		System.out.println("ans: " + convert("A", 1));
	}

	public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		char[] chars = s.toCharArray();
		int part = numRows - 1;
		int numOfPart = numRows + part - 1;
		int numColumns = part * (chars.length / numOfPart + 1);
		char[][] pic = new char[numRows][numColumns];
		// 初始化
		for (int i = 0; i < numRows; i++) {
			Arrays.fill(pic[i], '?');
			System.err.println(Arrays.toString(pic[i]));
		}
		// 构造二维图像
		int next = 0;
		int f = numRows - 1;
		for (int j = 0; j < numColumns && next < chars.length; j++) {
			for (int i = 0; i < numRows && next < chars.length; i++) {
				if (j % f == 0) {
					pic[i][j] = s.charAt(next++);
				} else if ((j % f + i) == f) {
					pic[i][j] = s.charAt(next++);
				}
			}
		}
		System.err.println("======输出结果======");
		for (int i = 0; i < numRows; i++) {
			System.err.println(Arrays.toString(pic[i]));
		}
		// 输出结果
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (pic[i][j] != '?') {
					ans.append(pic[i][j]);
				}
			}
		}
		return ans.toString();
	}
}
