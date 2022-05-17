package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Test953 {

	@Test
	public void test() {
		System.err.println("ans: " + isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
		System.err.println("ans: " + isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));
		System.err.println("ans: " + isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz"));
	}

	public boolean isAlienSorted(String[] words, String order) {
		PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
			int len = Math.min(o1.length(), o2.length());
			for (int i = 0; i < len; i++) {
				int result = order.indexOf(o1.charAt(i)) - order.indexOf(o2.charAt(i));
				if (result != 0) return result;
			}
			return o1.length() - o2.length();
		});
		pq.addAll(Arrays.asList(words));
//		System.out.println("===begin===");
//		while (pq.size() > 0) {
//			System.out.println("debug-top: " + pq.poll());
//		}
//		System.out.println("===end===");
		for (String word : words) {
//			System.out.println("top: " + pq.peek());
			if (!word.equals(pq.poll())) return false;
		}
		return true;
	}
}
