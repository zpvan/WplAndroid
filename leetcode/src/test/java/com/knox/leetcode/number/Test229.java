package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Test229 {
    @Test
    public List<Integer> majorityElement(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        int limit = nums.length / 3;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            if (orDefault + 1 > limit) {
                ans.add(num);
            }
            map.put(num, orDefault + 1);
        }
        return new ArrayList<>(ans);
    }
}
