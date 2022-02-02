package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test71 {

  @Test
  public void test() {}

  public String simplifyPath(String path) {
    String[] ps = path.split("/");
    Deque<String> deque = new ArrayDeque<>();
    for (String p : ps) {
      if ("..".equals(p)) {
        if (!deque.isEmpty()) deque.pollLast();
      } else if (!".".equals(p) && p.length() > 0) {
        deque.offerLast(p);
      }
    }
    StringBuilder ans = new StringBuilder();
    if (deque.isEmpty()) {
      ans.append("/");
    } else {
      while (!deque.isEmpty()) {
        ans.append("/");
        ans.append(deque.pollFirst());
      }
    }
    return ans.toString();
  }
}
