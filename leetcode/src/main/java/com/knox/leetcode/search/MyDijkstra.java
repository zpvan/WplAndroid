package com.knox.leetcode.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MyDijkstra {

  // A) MyGraph类
  // 1. 用邻接表表示的图
  private class MyGraph {
    // 2. 图中边的表示
    private class MyEdge {
      public int sid; // 边的起始顶点
      public int tid; // 边的终止顶点
      public int w; // 权重

      public MyEdge(int sid, int tid, int w) {
        this.sid = sid;
        this.tid = tid;
        this.w = w;
      }
    }

    // 3. 图拥有一个邻接表
    List<MyEdge>[] edges; // 邻接表，每个顶点都可能有一个List的邻接点
    int v; // 顶点个数

    public MyGraph(int v) {
      this.v = v;
      this.edges = new LinkedList[v];
      IntStream.range(0, v).forEach(i -> edges[i] = new LinkedList<>());
    }

    // 4. 成员函数，添加一条边
    public void addEdge(int s, int t, int w) {
      edges[s].add(new MyEdge(s, t, w));
    }

    // B) MyVertex类
    private class MyVertex {
      public int vid; // 顶点编号
      public int dist; // 从起始顶点到该顶点的距离

      public MyVertex(int vid, int dist) {
        this.vid = vid;
        this.dist = dist;
      }
    }

    // C) MyPriorityQueue, 对MyVertex.dist做小顶堆,
    // 比jdk的PriorityQueue多一个update方法
    private class MyPriorityQueue { // 目的是根据MyVertex.dist来构建小顶堆
      private MyVertex[] nodes; // 数组，从下标1开始存储数据
      private int v; // 堆的最大容量
      private int count; // 堆中的元素个数
      private Map<Integer, MyVertex> map = new HashMap<>();

      public MyPriorityQueue(int v) {
        // 不支持扩容
        this.nodes = new MyVertex[v + 1];
        this.v = v;
        this.count = 0;
      }

      // 移除堆顶元素
      public MyVertex poll() {
        if (count == 0) return null;
        MyVertex vertex = nodes[1];
        nodes[1] = nodes[count];
        count--;
        _heapifyUpToDown(nodes, count, 1);
        return vertex;
      }

      // 添加一个元素
      public void add(MyVertex vertex) {
        if (count > v) return; // 堆满了
        ++count;
        map.put(vertex.vid, vertex);
        nodes[count] = vertex;
        _heapifyDownToUp(nodes, count);
      }

      public void update(MyVertex vertex) {
        map.get(vertex.vid).dist = vertex.dist;
        _heapifyUpToDown(nodes, count , 1);
      }

      public boolean isEmpty() {
        return count == 0;
      }

      private void _heapifyDownToUp(MyVertex[] nodes, int end) {
        // 从下往上堆化
        int i = end;
        // 因为要做小顶堆，所以dist的判断是<
        while ((i/2 > 0) && (nodes[i].dist < nodes[i/2].dist)) {
          _swap(nodes, i, i/2);
          i = i/2;
        }
      }

      private void _heapifyUpToDown(MyVertex[] nodes, int down, int up) {
        // 从上往下堆化
        while (true) {
          int maxPos = up;
          if ((up*2 <= down) && (nodes[up].dist > nodes[up*2].dist)) maxPos = up*2;
          if ((up*2+1 <= down) && (nodes[maxPos].dist > nodes[up*2+1].dist)) maxPos = up*2+1;
          if (maxPos == up) break;
          _swap(nodes, up, maxPos);
          up = maxPos;
        }
      }

      private void _swap(MyVertex[] nodes, int x, int y) {
        MyVertex tmp = nodes[x];
        nodes[x] = nodes[y];
        nodes[y] = tmp;
      }
    }

    // D) Dijkstra算法本身
    public void dijkstra(int s, int t) { // 求从顶点s到顶点t的最短路径
      int[] predecessor = new int[this.v]; // 用来还原最短路径
      MyVertex[] vertices = new MyVertex[this.v];
      for (int i = 0; i < this.v; i++) {
        vertices[i] = new MyVertex(i, Integer.MAX_VALUE); // 初始化每个顶点到起始点的距离w为MAX
      }
      MyPriorityQueue queue = new MyPriorityQueue(this.v);
      boolean[] inqueue = new boolean[this.v]; // 标志是否进入过队列
      vertices[s].dist = 0; // 起始点的距离为0
      queue.add(vertices[s]);
      inqueue[s] = true;
      while (!queue.isEmpty()) {
        MyVertex minVertex = queue.poll(); // 取堆顶元素并删除
        if (minVertex.vid == t) break; // 最短路径生成了
        for (int i = 0; i < edges[minVertex.vid].size(); ++i) { // 遍历顶点的所有边
          MyEdge edge = edges[minVertex.vid].get(i); // 取出一条相连边
          MyVertex nextVertex = vertices[edge.tid]; // minVertex -> nextVertex
          if (minVertex.dist + edge.w < nextVertex.dist) { // 更新next的dist
            nextVertex.dist = minVertex.dist + edge.w;
            predecessor[nextVertex.vid] = minVertex.vid; // 记录最短路径中每个顶点的上个顶点
            if (inqueue[nextVertex.vid] == true) {
              queue.update(nextVertex);
            } else {
              queue.add(nextVertex);
              inqueue[nextVertex.vid] = true;
            }
          }
        }
      }
      // 输出最短路径
      System.out.println(s);
      _print(s, t, predecessor);
    }

    private void _print(int s, int t, int[] predecessor) {
      if (s == t) return;
      _print(s, predecessor[t], predecessor);
      System.out.println("->" + t);
    }
  }
}
