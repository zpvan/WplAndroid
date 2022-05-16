package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test49 {

	@Test
	public void test() {
		System.out.println("ans: " + groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> collect = Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
			// 返回 str 排序后的结果。
			// 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
			char[] cs = str.toCharArray();
			Arrays.sort(cs);
			return new String(cs);
		}));
		return new ArrayList<>(collect.values());
	}
}
