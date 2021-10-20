package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

public class Test310 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        // 准备阶段，记录每个节点的连接数
        int[] degrees = new int[n];
        // 记录点连接了谁
        Map<Integer, List<Integer>> map = new HashMap<>();
        IntStream.range(0, n).forEach(i -> map.put(i, new ArrayList<>()));
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // bfs, 从连接数为1的点开始搜索
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            ans.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer node = queue.remove();
                ans.add(node);
                // 与node相连的节点的集合
                List<Integer> connected = map.get(node);
                for (Integer e : connected) {
                    degrees[e]--;
                    if (degrees[e] == 1) queue.offer(e);
                }
            }
        }
        return ans;
    }
}
