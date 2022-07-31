package com.knox.leetcode.number;

public class Test427 {

	int[][] g;

	public Node construct(int[][] grid) {
		g = grid;
		return dfs(0, 0, g.length - 1, g.length - 1);
	}

	/**
	 * 假定函数Node dfs(int a, int b, int c, int d)返回"以(a,b)为左上角, 以(c,d)为右下角"所代表的矩阵的根节点
	 * 判断矩阵是否全为0或全为1：
	 * * 如果是则直接创建根节点（该节点四个子节点属性为空）并进行返回
	 * * 如果不是则创建根节点，递归创建四个子节点并进行赋值，利用左上角(a,b)和右下角(c,d)来算出横纵坐标的长度为c-a+1
	 * 和d-b+1，从而计算出将当前矩阵四等分所得到的子矩阵的左上角和右下角坐标。
	 */
	private Node dfs(int a, int b, int c, int d) {
		// 假设是否都相同
		boolean same = true;
		// 第一个元素记作f
		int f = g[a][b];
		for (int i = a; i <= c && same; i++) {
			for (int j = b; j <= d && same; j++) {
				if (f != g[i][j]) {
					same = false;
				}
			}
		}
		// 都相同，是叶子
		if (same) return new Node(f == 1, true);
		// 并非都相同，不是叶子，继续递归
		Node root = new Node(true, false);
		int dx = c - a + 1, dy = d - b + 1;
		root.topLeft = dfs(a, b, a + dx / 2 - 1, b + dy / 2 - 1);
		root.topRight = dfs(a, b + dy / 2, a + dx / 2 - 1, d);
		root.bottomLeft = dfs(a + dx / 2, b, c, b + dy / 2 - 1);
		root.bottomRight = dfs(a + dx / 2, b + dy / 2, c, d);
		return root;
	}

	private static class Node {
		public boolean val;
		public boolean isLeaf;
		public Node topLeft;
		public Node topRight;
		public Node bottomLeft;
		public Node bottomRight;


		public Node() {
			this.val = false;
			this.isLeaf = false;
			this.topLeft = null;
			this.topRight = null;
			this.bottomLeft = null;
			this.bottomRight = null;
		}

		public Node(boolean val, boolean isLeaf) {
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = null;
			this.topRight = null;
			this.bottomLeft = null;
			this.bottomRight = null;
		}

		public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
			this.val = val;
			this.isLeaf = isLeaf;
			this.topLeft = topLeft;
			this.topRight = topRight;
			this.bottomLeft = bottomLeft;
			this.bottomRight = bottomRight;
		}
	}

	;
}
