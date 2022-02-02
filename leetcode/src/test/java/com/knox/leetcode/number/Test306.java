package com.knox.leetcode.number;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Test306 {

	String number;
	int n;
	List<List<Integer>> list = new ArrayList<>();
	public boolean isAdditiveNumber(String num) {
		number = num;
		n = number.length();
		return dfs(0);
	}

    private boolean dfs(int u) {
	    int m = list.size();
	    if (u == n) return m >= 3;
	    int max = number.charAt(u) == '0' ? u + 1 : n;
	    List<Integer> cur = new ArrayList<>();
	    for (int i = u; i < max; i++) {
	        cur.add(0, number.charAt(i) - '0');
	        if (m < 2 || check(list.get(m - 2), list.get(m - 1), cur)) {
	            list.add(cur);
	            if (dfs(i + 1)) return true;
	            list.remove(list.size() - 1);
            }
        }
	    return false;
    }

    private boolean check(List<Integer> x, List<Integer> y, List<Integer> cur) {
        List<Integer> z = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < x.size() || i < y.size(); i++) {
            if (i < x.size()) t += x.get(i);
            if (i < y.size()) t += y.get(i);
            z.add(t % 10);
            t /= 10;
        }
        if (t > 0) z.add(t);
        boolean ok = z.size() == cur.size();
        for (int i = 0; i < z.size() && ok; i++) {
            if (z.get(i) != cur.get(i)) ok = false;
        }
        return ok;
    }
}
