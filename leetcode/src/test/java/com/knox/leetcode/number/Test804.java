package com.knox.leetcode.number;

import java.util.HashSet;
import java.util.Set;

public class Test804 {

	public int uniqueMorseRepresentations(String[] words) {
		String[] MorseCode = new String[]{
				".-", "-...", "-.-.", "-..",
				".", "..-.", "--.", "....",
				"..", ".---", "-.-", ".-..",
				"--", "-.", "---", ".--.",
				"--.-", ".-.", "...", "-",
				"..-", "...-", ".--", "-..-",
				"-.--", "--.."
		};
		Set<String> ans = new HashSet<>();
		for (String word : words) {
			StringBuilder sb = new StringBuilder();
			for (char c : word.toCharArray()) {
				sb.append(MorseCode[c - 'a']);
			}
			ans.add(sb.toString());
		}
		return ans.size();
	}
}
