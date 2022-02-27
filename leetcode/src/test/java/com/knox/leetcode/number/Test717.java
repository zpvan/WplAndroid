package com.knox.leetcode.number;

public class Test717 {

	public boolean isOneBitCharacter(int[] bits) {
		boolean ans = true;
		for (int i = bits.length - 2; i >= 0; i--) {
			if (bits[i] == 1) ans = !ans;
			else break;
		}
		return ans;
	}
}
