package com.knox.leetcode.number;

class Test206 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	class Solution {
		public ListNode reverseList(ListNode head) {
			ListNode tail = null;
			while (head != null) {
				ListNode next = head.next;
				head.next = tail;
				tail = head;
				head = next;
			}
			return tail;
		}
	}
}
