package com.knox.leetcode.number;

import org.junit.Test;

public class Test468 {

	@Test
	public void test() {
		System.out.println("ans: " + validIPAddress("172.16.254.1"));
		System.out.println("ans: " + validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
		System.out.println("ans: " + validIPAddress("256.256.256.256"));
		System.out.println("ans: " + validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
		System.out.println("ans: " + validIPAddress("1.1.1.1."));
	}

	public String validIPAddress(String queryIP) {
		if (isIPv4(queryIP)) return "IPv4";
		if (isIPv6(queryIP)) return "IPv6";
		return "Neither";
	}

	private boolean isIPv4(String queryIP) {
		System.err.println(queryIP + ", fi: " + queryIP.indexOf(".") + ", li: " + queryIP.lastIndexOf(".") + ", length: " + queryIP.length());
		if (queryIP.indexOf("\\.") == 0 || queryIP.lastIndexOf("\\.") == queryIP.length() - 1)
			return false;
		String[] ipv4 = queryIP.split("\\.");
		if (ipv4.length != 4) return false;
		for (String x : ipv4) {
			try {
				if (x.length() > 1 && x.charAt(0) == '0') return false;
				Integer ix = Integer.valueOf(x);
				if (ix < 0 || ix > 255) return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	private boolean isIPv6(String queryIP) {
		if (queryIP.indexOf(":") == 0 || queryIP.lastIndexOf(":") == queryIP.length() - 1)
			return false;
		String[] ipv6 = queryIP.split(":");
		if (ipv6.length != 8) return false;
		for (String x : ipv6) {
			if (x.length() > 4 || x.length() == 0) return false;
			try {
				Integer.valueOf(x.toLowerCase(), 16);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
