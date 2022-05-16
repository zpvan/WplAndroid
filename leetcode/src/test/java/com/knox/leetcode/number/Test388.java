package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Test388 {

	@Test
	public void test() {
		System.out.println("ans: " + lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
		System.out.println("ans: " + lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
		System.out.println("ans: " + lengthLongestPath("a"));
	}

	public int lengthLongestPath(String input) {
		List<String> files = new ArrayList<>();
		String[] inputs = input.split("\n");
		dfs(inputs, 0, new HashMap<>(), files);
		System.err.println(Arrays.toString(files.toArray()));
		if (files.size() == 0) return 0;
		return files.stream().map(it -> it.length()).max((o1, o2) -> o1 - o2).get();
	}

	private void dfs(String[] inputs, int index, Map<Integer, String> prefix, List<String> files) {
		if (index >= inputs.length) return;
		String in = inputs[index];
		int lt = in.lastIndexOf('\t') + 1;
		String realPath = in.substring(lt);
		prefix.put(lt, realPath);
		if (realPath.contains(".")) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <= lt; i++) {
				sb.append(prefix.get(i));
				sb.append(i == lt ? "" : "/");
			}
			files.add(sb.toString());
		}
		dfs(inputs, index + 1, prefix, files);
	}
}
