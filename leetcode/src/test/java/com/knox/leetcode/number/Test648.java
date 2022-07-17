package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test648 {

	@Test
	public void test() {
		List<String> l1 = new ArrayList<>();
		l1.add("cat");
		l1.add("bat");
		l1.add("rat");
		System.out.println("ans: " + replaceWords(l1, "the cattle was rattled by the battery"));

		List<String> l2 = new ArrayList<>();
		l2.add("a");
		l2.add("b");
		l2.add("c");
		System.out.println("ans: " + replaceWords(l2, "aadsfasf absbs bbab cadsfafs"));
	}

	public String replaceWords(List<String> dictionary, String sentence) {
		Node tree = new Node();
		// 构建字典树
		for (String root : dictionary) {
			Node curr = tree;
			char[] chars = root.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (curr.next[chars[i] - 'a'] == null) {
					curr.next[chars[i] - 'a'] = new Node();
				}
				Node node = curr.next[chars[i] - 'a'];
				if (i == chars.length - 1) {
					node.end = true;
				}
				curr = node;
			}
		}

		StringBuilder ans = new StringBuilder();
		String[] words = sentence.split(" ");
		for (String word : words) {
			StringBuilder sb = new StringBuilder();
			Node curr = tree;
			char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (curr.next[chars[i] - 'a'] != null) {
					Node node = curr.next[chars[i] - 'a'];
					sb.append(chars[i]);
					if (node.end) {
						break;
					} else {
						curr = node;
					}
				} else {
					sb.append(word.substring(i));
					break;
				}
			}
			ans.append(sb).append(" ");
		}

		return ans.toString().trim();
	}

	private class Node {
		Node[] next = new Node[26];
		boolean end;
	}
}
