package com.knox.leetcode.number;

import java.util.*;

public class Test496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调栈 + 散列表
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> n2Map = new HashMap<>();
        for (int n2 : nums2) {
            while (!deque.isEmpty() && deque.peekFirst() < n2) {
                n2Map.put(deque.removeFirst(), n2);
            }
            deque.addFirst(n2);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = n2Map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
