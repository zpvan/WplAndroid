package com.knox.leetcode.offer;

public class Test029 {

	public Node insert(Node head, int insertVal) {
		if (head == null) {
			// 空值
			head = new Node(insertVal);
			head.next = head;
			return head;
		}

		if (head.next == head) {
			// 单值
			head.next = new Node(insertVal, head);
			return head;
		}

		Node curr = head;
		boolean loop = false;
		int min = 0x3f3f3f;
		Node firstMin = null;
		// 大于等于两个值
		while (curr.next != null) {
			Node next = curr.next;
			if (curr.val <= min && !loop) {
				// 循环第一圈时, 找到最小值, 同时找第一个最小值
				min = curr.val;
				firstMin = curr;
			}
			if (next.val < curr.val) {
				// 补充找第一个最小值
				firstMin = next;
			}
			if (next.val >= insertVal && curr.val <= insertVal) {
				// 十分正常的情况
				curr.next = new Node(insertVal, next);
				break;
			}
			if (loop && next == firstMin) {
				// 循环一圈之后, 没从十分正常的情况退出循环, 则找到一个最小值与最大值之间退出循环
				curr.next = new Node(insertVal, next);
				break;
			}
			curr = next;
			if (curr == head) {
				// 循环一圈时, 添加标记
				loop = true;
			}
		}

		return head;
	}

	private class Node {
		public int val;
		public Node next;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _next) {
			val = _val;
			next = _next;
		}
	}
}
