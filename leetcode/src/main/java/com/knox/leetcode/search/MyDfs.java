package com.knox.leetcode.search;

import java.util.LinkedList;
import java.util.List;

public class MyDfs {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        // dfs
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        if (ans.size() <= level) {
            ans.add(new LinkedList<>());
        }
        List<Integer> list = ans.get(level);
        list.add(node.val);
        dfs(node.left, level + 1, ans);
        dfs(node.right, level + 1, ans);
    }

    private class TreeNode {
        int            val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
