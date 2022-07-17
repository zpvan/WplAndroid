package com.knox.leetcode.number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test1200 {

	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		Arrays.sort(arr);

		Map<Integer, List<List<Integer>>> map = new HashMap<>();
		int minAbs = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length - 1; i++) {
			int diff = arr[i + 1] - arr[i];
			minAbs = Math.min(minAbs, diff);
			List<Integer> l = new LinkedList<>();
			l.add(arr[i]);
			l.add(arr[i + 1]);
			List<List<Integer>> lli = map.getOrDefault(diff, new LinkedList<>());
			lli.add(l);
			map.put(diff, lli);
		}

		return map.get(minAbs);
	}
}
