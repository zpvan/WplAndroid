package com.knox.leetcode.number;

import java.util.HashMap;
import java.util.Map;

public class Test1748 {

   public int sumOfUnique(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      int ans = 0;
      for (int num : nums) {
         if (map.get(num) == null) {
            ans += num;
            map.put(num, 1);
         } else if (map.get(num) == 1) {
            ans -= num;
            map.put(num, 2);
         }
      }
      return ans;
   }
}
