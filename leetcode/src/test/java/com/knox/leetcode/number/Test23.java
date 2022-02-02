package com.knox.leetcode.number;

import java.util.Arrays;
import java.util.PriorityQueue;

class Test23 {
	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> smallTopHeap = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
		Arrays.stream(lists)
				.forEach(
						node -> {
							if (node != null) {
								smallTopHeap.add(node);
							}
						});

		ListNode ans = new ListNode();
		ListNode curr = ans;
		while (!smallTopHeap.isEmpty()) {
			ListNode top = smallTopHeap.poll();
			if (top.next != null) {
				smallTopHeap.add(top.next);
			}
			curr.next = new ListNode(top.val);
			curr = curr.next;
		}

		return ans.next;
	}

	private static class ListNode {
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
}
