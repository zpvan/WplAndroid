package com.knox.leetcode.number;

import org.junit.Test;

public class Test1108 {

	@Test
	public void test() {
		System.out.println("ans: " + defangIPaddr("1.1.1.1"));
		System.out.println("ans: " + defangIPaddr("255.100.50.0"));
	}

	public String defangIPaddr(String address) {
		StringBuilder sb = new StringBuilder();
		for (Character c : address.toCharArray()) {
			if (c.equals('.')) {
				sb.append("[.]");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
