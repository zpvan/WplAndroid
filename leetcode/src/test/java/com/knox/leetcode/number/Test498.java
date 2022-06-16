package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test498 {

	@Test
	public void test() {
		System.out.println("ans: " + Arrays.toString(findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
	}

	int h, w;
	public int[] findDiagonalOrder(int[][] mat) {
		h = mat.length;
		w = mat[0].length;
		int[] ans = new int[h * w];

		int[] dx = new int[]{1, 1, 0, -1, 0, 1};
		int[] dy = new int[]{-1, 0, 1, 1, 1, 0};
		/**
		 * 当"右上"有, 则走"右上" {dx[0], dy[0]}
		 * 当"右上"无, "右"有, 则走"右" {dx[1], dy[1]} 并切换成"左下"模式
		 * 当"右上"无, "右"无, "下"有, 则走"下" {dx[2], dy[2]} 并切换成"左下"模式
		 * 当"右上"无, "右"无, "下"无, 则结束
		 *
		 * 当"左下"有, 则走"左下" {dx[3], dy[3]}
		 * 当"左下"无, "下"有, 则走"下" {dx[4], dy[4]} 并切换成"右上"模式
		 * 当"左下"无, "下"无, "右"有, 则走"右" {dx[5], dy[5]} 并切换成"右上"模式
		 * 当"左下"无, "下"无, "右"无, 则结束
		 */
		boolean rUp = true;
		int[] pos = new int[]{0, 0};
		boolean end = false;
		int ans_i = 0;
		// Let's go
		while (!end) {
			System.err.println("x: " + pos[1] + ", y: " + pos[1]);
			ans[ans_i++] = mat[pos[1]][pos[0]];
			if (rUp) {
				// 右上模式
				if (validPos(pos[0] + dx[0], pos[1] + dy[0])) {
					pos[0] = pos[0] + dx[0];
					pos[1] = pos[1] + dy[0];
				} else if (validPos(pos[0] + dx[1], pos[1] + dy[1])) {
					pos[0] = pos[0] + dx[1];
					pos[1] = pos[1] + dy[1];
					rUp = false;
				} else if (validPos(pos[0] + dx[2], pos[1] + dy[2])) {
					pos[0] = pos[0] + dx[2];
					pos[1] = pos[1] + dy[2];
					rUp = false;
				} else {
					end = true;
				}
			} else {
				// 左下模式
				if (validPos(pos[0] + dx[3], pos[1] + dy[3])) {
					pos[0] = pos[0] + dx[3];
					pos[1] = pos[1] + dy[3];
				} else if (validPos(pos[0] + dx[4], pos[1] + dy[4])) {
					pos[0] = pos[0] + dx[4];
					pos[1] = pos[1] + dy[4];
					rUp = true;
				} else if (validPos(pos[0] + dx[5], pos[1] + dy[5])) {
					pos[0] = pos[0] + dx[5];
					pos[1] = pos[1] + dy[5];
					rUp = true;
				} else {
					end = true;
				}
			}
		}

		return ans;
	}

	private boolean validPos(int x, int y) {
		return 0 <= x && x < w && 0 <= y && y < h;
	}
}
