package com.knox.leetcode.number;

import java.util.Arrays;

public class Test954 {

   public boolean canReorderDoubled(int[] arr) {
      boolean[] visited = new boolean[arr.length];
      // 排序
      Arrays.sort(arr);
      // 双指针, l 跟 r
      int l = 0, r = 1;
      // r 一直 +1 右移
      int ans = 0;
      while (r < arr.length && l < arr.length) {
         if (visited[l]) {
            l++;
            continue;
         }
         if (visited[r]) {
            r++;
            continue;
         }
         if (l >= r) {
            r++;
            continue;
         }
         if (arr[r] < 0) {
            // 大值小于 0 时
            if (arr[l] == 2 * arr[r]) {
               ans++;
               visited[l] = true;
               visited[r] = true;
               l++;
               r++;
            } else if (arr[l] < 2 * arr[r]) {
               l++;
            } else {
               r++;
            }
         } else {
            // 大值大于等于 0 时
            if (2 * arr[l] == arr[r]) {
               ans++;
               visited[l] = true;
               visited[r] = true;
               l++;
               r++;
            } else if (2 * arr[l] < arr[r]) {
               l++;
            } else {
               r++;
            }
         }
      }
      return ans == arr.length / 2;
   }
}
