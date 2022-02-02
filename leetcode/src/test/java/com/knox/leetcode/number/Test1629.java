package com.knox.leetcode.number;

public class Test1629 {

	public char slowestKey(int[] releaseTimes, String keysPressed) {
		int total = releaseTimes.length;
		char ans = keysPressed.charAt(0);
		int max = releaseTimes[0];
		for (int i = 1; i < releaseTimes.length; i++) {
			int d = releaseTimes[i] - releaseTimes[i - 1];
			if (d > max || (d == max && keysPressed.charAt(i) > ans)) {
				ans = keysPressed.charAt(i);
				max = d;
			}
		}
		return ans;
	}
}
