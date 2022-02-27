package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Test743 {

	@Test
	public void test() {
		System.err.println("ans: " + networkDelayTime(
				new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
	}

	/**
	 * 网络延迟时间
	 *
	 * @param times 列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，
	 *              其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
	 * @param n     网络节点数
	 * @param k     从某个节点 K 发出一个信号。
	 * @return 需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
	 */
	public int networkDelayTime(int[][] times, int n, int k) {
		int ans = 0;
//		ans = FloydPlusMatrix(times, n, k);
//		ans = SimpleDijkstraPlusMatrix(times, n, k);
//		ans = HeapDijkstraPlusArray(times, n, k);
		ans = BellmanFordPlusArray(times, n, k);
		return ans;
	}

	/**
	 * 跑一遍 Floyd，可以得到「从任意起点出发，到达任意起点的最短距离」。
	 * 多源汇最短路 O(N^3)
	 */
	public int FloydPlusMatrix(int[][] times, int n, int k) {
		int N = n + 1;
		// 邻接矩阵：w[a][b] = c 表示从 a 到 b 有权重为 c 的边
		int[][] w = new int[N][N];
		int INF = 0x3f3f3f3f;
		// 初始化邻接矩阵
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				w[i][j] = i == j ? 0 : INF;
			}
		}
		// 存图
		for (int[] t : times) {
			int u = t[0], v = t[1], c = t[2];
			w[u][v] = c;
		}
		// 最短路
		floyd(n, w);
		// 遍历答案
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = Math.max(ans, w[k][i]);
		}
		return ans >= INF / 2 ? -1 : ans;
	}

	private void floyd(int n, int[][] w) {
		// floyd 基本流程为三层循环
		// 枚举中转点 - 枚举起点 - 枚举终点 - 松弛操作
		for (int p = 1; p <= n; p++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					w[i][j] = Math.min(w[i][j], w[i][p] + w[p][j]);
				}
			}
		}
	}

	/**
	 * 朴素Dijkstra
	 * 单源最短路 O(N^2)
	 */
	public int SimpleDijkstraPlusMatrix(int[][] times, int n, int k) {
		System.err.println("======SimpleDijkstraPlusMatrix======");
		int N = n + 1;
		// 邻接矩阵：w[a][b] = c 表示从 a 到 b 有权重为 c 的边
		int[][] w = new int[N][N];
		int INF = 0x3f3f3f3f;
		// 初始化邻接矩阵
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				w[i][j] = i == j ? 0 : INF;
			}
		}
		// 存图
		for (int[] t : times) {
			int u = t[0], v = t[1], c = t[2];
			w[u][v] = c;
		}
		// 最短路
		// dist[x] = y 代表从 源点/起点 到 x 的最短距离为y
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		// 只有起点最短距离为0
		dist[k] = 0;
		simpleDijkstra(dist, n, w);
		System.err.println("dist: " + Arrays.toString(dist));
		// 遍历答案
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = Math.max(ans, dist[i]);
		}
		return ans > INF / 2 ? -1 : ans;
	}

	private void simpleDijkstra(int[] dist, int n, int[][] w) {
		// 记录哪些点已经被更新过
		boolean[] vis = new boolean[n + 1];
		// 起始先将所有的点标记为 未更新 和 距离为正无穷
		Arrays.fill(vis, false);
		// 迭代n次
		for (int p = 1; p <= n; p++) {
			// 每次找到 最短距离最小 且 未被更新 的点t
			int t = -1;
			for (int i = 1; i <= n; i++) {
				if (!vis[i] && (t == -1 || dist[i] < dist[t])) {
					t = i;
				}
			}
			// 标记点 t 为已更新
			vis[t] = true;
			// 用点 t 的「最小距离」更新其他点
			for (int i = 1; i <= n; i++) {
				dist[i] = Math.min(dist[i], dist[t] + w[t][i]);
			}
		}
	}

	/**
	 * 堆优化Dijkstra
	 * 单源最短路 O(M*logN)；M为边数，N为点数
	 */
	public int HeapDijkstraPlusArray(int[][] times, int n, int k) {
		System.err.println("======HeapDijkstraPlusArray======");
		List<Edge> w = new ArrayList<>();
		// 存图
		for (int[] t : times) {
			int u = t[0], v = t[1], c = t[2];
			w.add(new Edge(u, v, c));
		}
		// 最短路
		// dist[x] = y 代表从 源点/起点 到 x 的最短距离为y
		int N = n + 1;
		int INF = 0x3f3f3f3f;
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		// 只有起点最短距离为0
		dist[k] = 0;
		heapDijkstra(dist, n, k, w);
		System.err.println("dist: " + Arrays.toString(dist));
		// 遍历答案
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = Math.max(ans, dist[i]);
		}
		return ans > INF / 2 ? -1 : ans;
	}

	private void heapDijkstra(int[] dist, int n, int k, List<Edge> w) {
		// 记录哪些点已经被更新过
		boolean[] vis = new boolean[n + 1];
		// 起始先将所有的点标记为 未更新 和 距离为正无穷
		Arrays.fill(vis, false);
		// 使用 优先队列 存储所有可用于更新的点
		// 以 (点编号, 到起点的距离) 进行存储，优先弹出「最短距离」较小的点
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		q.add(new int[]{k, 0});
		while (!q.isEmpty()) {
			// 每次从「优先队列」中弹出
			int[] poll = q.poll();
			int id = poll[0], step = poll[1];
			// 如果弹出的点被标记「已更新」，则跳过
			if (vis[id]) continue;
			// 标记该点「已更新」，并使用该点更新其他点的「最短距离」
			vis[id] = true;
			for (Edge e : w) {
				System.out.println("id: " + id + ", e: " + e);
				if (e.b == id && (dist[e.a] > step + e.c)) {
					dist[e.a] = step + e.c;
					q.add(new int[]{e.a, dist[e.a]});
				}
				if (e.a == id && (dist[e.b] > step + e.c)) {
					dist[e.b] = step + e.c;
					q.add(new int[]{e.b, dist[e.b]});
				}
			}
		}
	}

	/**
	 * BellmanFord算法
	 * 单源最短路 O(M*N)；M为边数，N为点数
	 */
	public int BellmanFordPlusArray(int[][] times, int n, int k) {
		List<Edge> w = new ArrayList<>();
		// 存图
		for (int[] t : times) {
			int u = t[0], v = t[1], c = t[2];
			w.add(new Edge(u, v, c));
		}
		// 最短路
		int N = n + 1;
		int INF = 0x3f3f3f3f;
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		// 只有起点最短距离为0
		dist[k] = 0;
		bf(dist, n, w);
		// 遍历答案
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = Math.max(ans, dist[i]);
		}
		return ans > INF / 2 ? -1 : ans;
	}

	private void bf(int[] dist, int n, List<Edge> w) {
		// 迭代 n 次
		for (int p = 1; p <= n; p++) {
			int[] prev = dist.clone();
			// 每次都使用上一次迭代的结果，执行松弛操作
			for (Edge e : w) {
				int a = e.a, b = e.b, c = e.c;
				dist[b] = Math.min(dist[b], prev[a] + c);
			}
		}
	}

	public int SpfaPlusArray(int[][] times, int n, int k) {
		return 0;
	}

	class Edge {
		public int a, b, c;

		Edge(int _a, int _b, int _c) {
			a = _a;
			b = _b;
			c = _c;
		}

		@Override
		public String toString() {
			return "Edge{" +
					"a=" + a +
					", b=" + b +
					", c=" + c +
					'}';
		}
	}
}
