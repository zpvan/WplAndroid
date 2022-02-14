package com.knox.leetcode.number;

public class Test1189 {

	// balloon
	public int maxNumberOfBalloons(String text) {
		int[] count = new int[26];
		for (char c : text.toCharArray()) {
			count[c - 'a']++;
		}
		return Math.min(count[1], // b
				Math.min(count[0], // a
						Math.min(count[11] / 2, // l
								Math.min(count[14] / 2, // o
										count[13]) // n
						)));
	}
}
