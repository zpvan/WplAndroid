package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.List;

public class Test241 {

	char[] cs;

	public List<Integer> diffWaysToCompute(String expression) {
		cs = expression.toCharArray();
		return dfs(0, cs.length - 1);
	}

	private List<Integer> dfs(int l, int r) {
		List<Integer> ans = new ArrayList<>();

		for (int i = l; i <= r; i++) {
			if (cs[i] >= '0' && cs[i] <= '9') continue;
			List<Integer> l1 = dfs(l, i - 1), l2 = dfs(i + 1, r);
			// 有操作符, 分两半
			for (int a : l1) {
				for (int b : l2) {
					int cur = 0;
					if (cs[i] == '+') cur = a + b;
					else if (cs[i] == '-') cur = a - b;
					else cur = a * b;
					ans.add(cur);
				}
			}
		}

		// 纯数字, 无操作符
		if (ans.isEmpty()) {
			int cur = 0;
			for (int i = l; i <= r; i++) {
				cur = cur * 10 + (cs[i] - '0');
			}
			ans.add(cur);
		}

		return ans;
	}
}
