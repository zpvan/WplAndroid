package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test500 {

	public String[] findWords(String[] words) {
		/**
		 * qwertyuiop
		 * asdfghjkl
		 * zxcvbnm
		 */
		Map<Byte, Integer> byteMap = new HashMap<>();
		for (byte b : "qwertyuiop".getBytes()) {
			byteMap.put(b, 1);
		}
		for (byte b : "asdfghjkl".getBytes()) {
			byteMap.put(b, 2);
		}
		for (byte b : "zxcvbnm".getBytes()) {
			byteMap.put(b, 3);
		}
		List<String> ans = new ArrayList<>();
		for (String w : words) {
			int line = 0;
			for (byte b : w.toLowerCase().getBytes()) {
				int bLine = byteMap.get(b);
				if (line == 0) {
					line = bLine;
				} else {
					if (line != bLine) {
						line = -1;
						break;
					}
				}
			}
			if (line != -1) {
				ans.add(w);
			}
		}
		return ans.toArray(new String[]{});
	}
}
