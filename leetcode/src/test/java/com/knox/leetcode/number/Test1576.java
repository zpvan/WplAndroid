package com.knox.leetcode.number;

public class Test1576 {

	public String modifyString(String s) {
		int limit = s.length();
		char[] ans = s.toCharArray();
		for (int i = 0; i < limit; i++) {
			if (ans[i] != '?') continue;
			for (char c = 'a'; c <= 'c'; c++) {
				if (((i - 1 >= 0) && ans[i - 1] == c)
						|| ((i + 1 < limit) && ans[i + 1] == c)) {
					continue;
				}
				ans[i] = c;
				break;
			}
		}
		return new String(ans);
	}
}
