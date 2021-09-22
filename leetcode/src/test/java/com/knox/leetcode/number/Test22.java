package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.List;

class Test22 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, n, "", ans);
        return ans;
    }

    private void dfs(int left, int right, String cur, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(cur);
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(left - 1, right, cur + "(", ans);
        }
        if (right > 0) {
            dfs(left, right - 1, cur + ")", ans);
        }
    }
}
