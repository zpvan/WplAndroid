package com.knox.leetcode.number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test429 {

   public List<List<Integer>> levelOrder(Node root) {
      List<List<Integer>> ans = new ArrayList<>();
      if (root == null) return ans;
      // bfs
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(root);

      while (!queue.isEmpty()) {
         List<Integer> tmp = new ArrayList<>();
         int size = queue.size();
         for (int i = 0; i < size; i++) {
            Node poll = queue.poll();
            tmp.add(poll.val);
            queue.addAll(poll.children);
         }
         ans.add(tmp);
      }

      return ans;
   }

   private class Node {
      public int val;
      public List<Node> children;

      public Node() {}

      public Node(int _val) {
         val = _val;
      }

      public Node(int _val, List<Node> _children) {
         val = _val;
         children = _children;
      }
   };
}
