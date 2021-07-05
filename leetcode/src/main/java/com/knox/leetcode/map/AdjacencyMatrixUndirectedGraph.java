package com.knox.leetcode.map;

import java.util.LinkedList;
import java.util.Queue;

/** 无向图 存储方式：邻接矩阵 */
class AdjacencyMatrixUndirectedGraph {

  private int v; // 顶点的个数
  private LinkedList<Integer> adj[]; // 邻接表

  private boolean found = false;

  public AdjacencyMatrixUndirectedGraph(int v) {
    this.v = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int s, int t) { // 无向图一条边存两次
    adj[s].add(t);
    adj[t].add(s);
  }

  /**
   * 搜索一条从s到t的路径，bfs求得的路径就是从s到t的最短路径
   *
   * @param s 起始顶点
   * @param t 终止顶点
   *     <p>visited 是用来记录已经被访问的顶点，用来避免顶点被重复访问。 queue 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。 prev 用来记录搜索路径。
   */
  public void bfs(int s, int t) {
    if (s == t) {
      return;
    }
    boolean[] visited = new boolean[v];
    visited[s] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    int[] prev = new int[v];
    for (int i = 0; i < v; i++) {
      prev[v] = -1;
    }
    while (queue.size() != 0) {
      int w = queue.poll();
      for (int i = 0; i < adj[w].size(); i++) {
        int q = adj[w].get(i);
        if (!visited[q]) {
          prev[q] = w;
          if (q == t) {
            print(prev, s, t);
            return;
          }
          visited[q] = true;
          queue.add(q);
        }
      }
    }
  }

  /**
   * @param s
   * @param t
   */
  public void dfs(int s, int t) {
    found = false;
    boolean[] visited = new boolean[v];
    int[] prev = new int[v];
    for (int i = 0; i < v; i++) {
      prev[i] = -1;
    }
    recurDfs(s, t, visited, prev);
    print(prev, s, t);
  }

  private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
    if (found) {
      return;
    }
    visited[w] = true;
    if (w == t) {
      found = true;
      return;
    }
    for (int i = 0; i < adj[w].size(); i++) {
      int q = adj[w].get(i);
      if (!visited[q]) {
        prev[q] = w;
        recurDfs(q, t, visited, prev);
      }
    }
  }

  private void print(int[] prev, int s, int t) {
    if (prev[t] != -1 && t != s) {
      print(prev, s, prev[t]);
    }
    System.out.println(t + "");
  }
}
