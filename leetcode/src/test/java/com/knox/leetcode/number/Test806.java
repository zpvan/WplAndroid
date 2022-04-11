package com.knox.leetcode.number;

public class Test806 {

   public int[] numberOfLines(int[] widths, String s) {
      int ans = 0, wrote = 0;
      for (char c : s.toCharArray()) {
         int width = widths[c - 'a'];
         if (wrote + width > 100) {
            ans++;
            wrote = width;
         } else {
            wrote += width;
         }
      }
      return new int[] {ans, wrote};
   }
}
