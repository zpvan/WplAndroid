package com.knox.leetcode.number;

public class Test208 {

	private class Trie {

		boolean end = false;
		Trie[] child = new Trie[26];

		public Trie() {

		}

		public void insert(String word) {
			char[] chars = word.toCharArray();
			Trie curr = this;
			for (int i = 0; i < chars.length; i++) {
				if (curr.child[chars[i] - 'a'] == null) {
                   curr.child[chars[i] - 'a'] = new Trie();
				}
				curr = curr.child[chars[i] - 'a'];
				if (i == chars.length - 1) {
					curr.end = true;
				}
			}
		}

		public boolean search(String word) {
			char[] chars = word.toCharArray();
			Trie curr = this;
			for (char aChar : chars) {
				if (curr.child[aChar - 'a'] == null) {
					return false;
				}
				curr = curr.child[aChar - 'a'];
			}
			return curr.end;
		}

		public boolean startsWith(String prefix) {

		}
	}
}
