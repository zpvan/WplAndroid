package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Test787 {


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

    }

    // dijkstra 算法作用于 带正权值的有向/无向图, 求最短路径

    // A) Graph
    // 1. 用邻接表表示的图
    private class Graph {
        // 2. 图中边的表示
        private class Edge {
            public int sid; //边的起始顶点
            public int tid; //边的终止顶点
            public int w;   //权重

            public Edge(int sid, int tid, int w) {
                this.sid = sid;
                this.tid = tid;
                this.w = w;
            }
        }

        // 3. 图拥有一个邻接表
        List<Edge>[] edges; // 邻接表, 每个顶点都可能有一个List的相邻节点
        int v;              // 顶点个数

        public Graph(int v) {
            this.v = v;
            this.edges = new LinkedList[v];
            IntStream.range(0, v).forEach(i -> edges[i] = new LinkedList<>());
        }

        // 4. 成员函数, 添加一条边
        public void addEdge(int s, int t, int w) {
            edges[s].add(new Edge(s, t, w));
        }
    }

    // B) Vertex
    private class Vertex {
        public int vid; // 顶点编号
        public int dist; // 从起始顶点到这个顶点的距离

        public Vertex(int vid, int dist) {
            this.vid = vid;
            this.dist = dist;
        }
    }

    // C) MyPriorityQueue, 对Java的PriorityQueue补充update方法
    private class MyPriorityQueue { // 目的是根据Vertex.dist来构建小顶堆
        private Vertex[] nodes; // 数组, 从下标1开始存储数据
        private int v; // 堆的最大容量
        private int count; // 堆中的元素个数

        public MyPriorityQueue(int v) {
            // 不支持扩容
            this.nodes = new Vertex[v + 1];
            this.v = v;
            this.count = 0;
        }

        public Vertex poll() {
            if (count == 0) return null;
            Vertex vertex = nodes[1];
            nodes[1] = nodes[count];
            count--;
            _heapify(nodes, count, 1);
            return vertex;
        }

        public void add(Vertex vertex) {
            if (count > v) return; // 堆满了
            ++count;
            nodes[count] = vertex;
            _heapifyUpToDown(nodes, count);

        }

        public void update(Vertex vertex) {

        }

        public boolean isEmpty() {
            return count == 0;
        }

        private void _heapifyUpToDown(Vertex[] nodes, int count) {
            int i = count;
            // 因为要做小顶堆, 所以dist判断的是<
            while ((i/2 > 0) && (nodes[i].dist < nodes[i/2].dist)) {
                _swap(nodes, i, i/2);
                i = i/2;
            }
        }

        private void _swap(Vertex[] nodes, int x, int y) {
            Vertex tmp = nodes[x];
            nodes[x] = nodes[y];
            nodes[y] = tmp;
        }


    }
}
