package com.knox.leetcode.number;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test890 {

	public List<String> findAndReplacePattern(String[] words, String pattern) {
       List<String> ans = new LinkedList<>();
       for (String word : words) {
		   if (match(word, pattern) && match(pattern, word)) {
			   ans.add(word);
		   }
	   }
       return ans;
	}

	private boolean match(String word, String pattern) {
		Map<Character, Character> map = new HashMap<>();
		if (word.length() != pattern.length()) return false;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!map.containsKey(c)) {
				map.put(c, pattern.charAt(i));
			} else {
				if (map.get(c) != pattern.charAt(i)) return false;
			}
		}

		return true;
	}
}
