package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test911 {

	@Test
	public void test() {
		TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
				// 表示只有两个候选人0, 1
				new int[]{0, 1, 1, 0, 0, 1, 0},
				// 表示第几分钟进行投票
				new int[]{0, 5, 10, 15, 20, 25, 30});
		System.err.println("q(3)=" + topVotedCandidate.q(3));
		System.err.println("q(12)=" + topVotedCandidate.q(12));
		System.err.println("q(25)=" + topVotedCandidate.q(25));
		System.err.println("q(15)=" + topVotedCandidate.q(15));
		System.err.println("q(24)=" + topVotedCandidate.q(24));
		System.err.println("q(8)=" + topVotedCandidate.q(8));
	}

	@Test
	public void test2() {
		TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
				// 表示只有两个候选人0, 1
				new int[]{0, 0, 0, 0, 1},
				// 表示第几分钟进行投票
				new int[]{0, 6, 39, 52, 75});
		System.err.println("q(45)=" + topVotedCandidate.q(45));
		System.err.println("q(49)=" + topVotedCandidate.q(49));
		System.err.println("q(59)=" + topVotedCandidate.q(59));
		System.err.println("q(68)=" + topVotedCandidate.q(68));
		System.err.println("q(42)=" + topVotedCandidate.q(42));
		System.err.println("q(99)=" + topVotedCandidate.q(99));
		System.err.println("q(26)=" + topVotedCandidate.q(26));
		System.err.println("q(78)=" + topVotedCandidate.q(78));
		System.err.println("q(43)=" + topVotedCandidate.q(43));
	}

	private static class MyPair<K, V> {
		public K key;
		public V value;

		public MyPair(K k, V v) {
			key = k;
			value = v;
		}
	}

	class TopVotedCandidate {

		// map<k=时间点, v=最高票数的候选人编号>
		private Map<Integer, Integer> map = new HashMap<>();
		// 存储times
		private int[] times = null;

		public TopVotedCandidate(int[] persons, int[] times) {
			// map<k=候选人编号, v=候选人的目前选票>
			Map<Integer, Integer> tmpMap = new HashMap<>();
			// 目前最高选票的候选人编号 及 选票
			MyPair<Integer, Integer> now = new MyPair<>(-1, 0);
			// 循环N次
			for (int i = 0; i < times.length; i++) {
				int cd = persons[i];
				// 拿出候选人cd目前的选票结果,并且+1
				Integer voteOfCd = tmpMap.getOrDefault(cd, 0);
				voteOfCd++;
				tmpMap.put(cd, voteOfCd);
				// 判断是否要切换最高得票数的候选人
				if (voteOfCd >= now.value) {
					now.key = cd;
					now.value = voteOfCd;
				}

				System.err.println("put(key=" + times[i] + ",value=" + now.key + ")");
				map.put(times[i], now.key);
				this.times = Arrays.copyOf(times, times.length);
			}
		}

		public int q(int t) {
			int key = t > times[times.length - 1] ? times.length - 1 : find(t);
			System.err.println("t=" + t + ",key=" + key);
			return map.get(times[key]).intValue();
		}

		private int find(int t) {
			// 找到 <=t 的最大 time
			int l = 0;
			int r = times.length - 1;
			int key = -1;
			while (l <= r) {
				int mid = (l + r) / 2;
				if (times[mid] == t) {
					key = mid;
					break;
				}
				if (times[mid] > t && mid > 0 && times[mid - 1] < t) {
					key = mid - 1;
					break;
				}
				if (times[mid] > t) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
				continue;
			}
			return key;
		}
	}
}


