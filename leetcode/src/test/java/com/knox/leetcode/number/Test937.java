package com.knox.leetcode.number;

import org.junit.Test;

public class Test937 {

   @Test
   public void test() {
      System.out.println("ans: " + reorderLogFiles(new String[]{"s1 2 3"}));
   }

   public String[] reorderLogFiles(String[] logs) {
      /**
       * 第一个是标致符
       * 字母按字典序
       * 数字按原来的相对序
       * 字母都在数字前面
       */

      for (String log : logs) {
         String header = log.substring(0, log.indexOf(" "));
         String payload = log.substring(log.indexOf(" "));
         int type = 2; // 1 => 字母, 2 => 数字
         String[] strs = payload.split(" ");
         for (String str : strs) {
            boolean matches = str.matches("[0-9]+");
            if (!matches) {
               type = 1;
               break;
            }
         }
         System.out.println("type: " + type);
      }

      return null;
   }
}
