package com.knox.leetcode.number;

import java.util.List;

public class Test417 {

   /*
    * 从源点（格子）流向汇点（海域）是按照高度从高到低（非严格）的规则，那么反过来从
    * 海域到格子则是按照从低到高（非严格）规则进行，同时本身处于边缘的格子与海域联通
    *
    * 由两遍 BFS/DFS 分别从与当前海域直接相连的边缘格子出发，统计能够流向当前海域的
    * 格子集合，最后求两个集合的交集
    */
   public List<List<Integer>> pacificAtlantic(int[][] heights) {
      return null;
   }
}
