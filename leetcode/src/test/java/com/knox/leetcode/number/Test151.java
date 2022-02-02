package com.knox.leetcode.number;

class Test151 {
	public String reverseWords(String s) {
		String[] ss = s.split(" ");
		int l = 0, r = ss.length - 1;
		while (l <= r) {
			String temp = ss[l];
			ss[l] = ss[r];
			ss[r] = temp;
			l++;
			r--;
		}
		StringBuilder sb = new StringBuilder();
		for (String ele : ss) {
			String newEle = ele.trim();
			if (newEle.length() == 0) {
				continue;
			}
			sb.append(newEle).append(" ");
		}
		return sb.toString().trim();
	}
}
