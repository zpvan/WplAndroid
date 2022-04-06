package com.knox.leetcode.number;

import org.junit.Test;

public class Test796 {

   @Test
   public void test() {
      System.err.println("ans=" + rotateString("abcde","cdeab"));
   }

   public boolean rotateString(String s, String goal) {
      if (s.length() != goal.length()) return false;
      
      int anchor = goal.charAt(0);
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) != anchor) continue;
         boolean ans = true;
         for (int j = 0; j < s.length(); j++) {
            if (s.charAt((j + i) % s.length()) != goal.charAt(j)) {
               ans = false;
               break;
            }
         }
         if (ans) return true;
      }
      return false;
   }
}
