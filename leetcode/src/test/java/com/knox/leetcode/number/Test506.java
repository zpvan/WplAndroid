package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Test506 {

	@Test
	public void test() {
		int[] score = new int[] {10,3,8,9,4};
    	System.err.println("ans: " + Arrays.toString(findRelativeRanks(score)));
	}

	public String[] findRelativeRanks(int[] score) {
		String[] ans = new String[score.length];
		PriorityQueue<String> pq = new PriorityQueue(score.length, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Integer.parseInt(s2.split("&")[0]) - Integer.parseInt(s1.split("&")[0]);
			}
		});
		AtomicInteger pos = new AtomicInteger();
		Arrays.stream(score).boxed().forEach(ele -> pq.add(ele + "&" + (pos.getAndIncrement())));
		int rank = 1;
		while (!pq.isEmpty()) {
			String[] split = pq.poll().split("&");
			String str = "";
			switch (rank) {
				case 1:
					str = "Gold Medal";
					break;

				case 2:
					str = "Silver Medal";
					break;

				case 3:
					str = "Bronze Medal";
					break;

				default:
					str = String.valueOf(rank);
			}
			ans[Integer.parseInt(split[1])] = str;
			rank++;
		}
		return ans;
	}
}
