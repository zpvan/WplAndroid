package com.knox.leetcode.number;

class Test142 {
	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode slow = head, fast = head;
		do {
			if (fast == null || fast.next == null) {
				return null;
			}
			slow = slow.next;
			fast = fast.next.next;
		} while (slow != fast);

		ListNode slow2 = head;
		while (slow2 != slow) {
			slow2 = slow2.next;
			slow = slow.next;
		}
		return slow;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
