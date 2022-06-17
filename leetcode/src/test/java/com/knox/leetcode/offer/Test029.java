package com.knox.leetcode.offer;

public class Test029 {

	public Node insert(Node head, int insertVal) {
       if (head == null) {
		   // 空值
		   return new Node(insertVal);
	   }

	   Node curr = head;
	   while (curr.next != null) {
		   Node next = curr.next;
		   
	   }
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
