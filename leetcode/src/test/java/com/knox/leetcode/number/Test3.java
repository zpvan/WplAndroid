package com.knox.leetcode.number;

import java.util.HashMap;
import java.util.Map;

public class Test3 {

	public int lengthOfLongestSubstring(String s) {
		/**
		 * 滑动窗口
		 */
		int ans = 0;
		char[] chars = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		int start = -1;
		for (int i = 0; i < chars.length; i++) {
			char aChar = chars[i];
			Integer orDefault = map.getOrDefault(aChar, -1);
			if (orDefault != -1) {
				start = Math.max(start, orDefault);
			}
			map.put(aChar, i);
			ans = Math.max(ans, i - start);
		}
		return ans;
	}
}
