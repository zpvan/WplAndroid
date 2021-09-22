package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test165 {
  @Test
  public void test() {
    String version1 = "7.5.3.0";
    String version2 = "7.5.3";
    System.out.println("output: " + compareVersion(version1, version2));
  }

  public int compareVersion(String version1, String version2) {
    List<Integer> v1 =
        Arrays.stream(version1.split("\\."))
            .map(s -> Integer.valueOf(s))
            .collect(Collectors.toList());
    List<Integer> v2 =
        Arrays.stream(version2.split("\\."))
            .map(s -> Integer.valueOf(s))
            .collect(Collectors.toList());
    int size = Math.min(v1.size(), v2.size());
    System.out.println("compare begin, v1: " + Arrays.toString(v1.toArray())
            + ", v2: " + Arrays.toString(v2.toArray()));
    for (int i = 0; i < size; i++) {
      int i1 = v1.get(i);
      int i2 = v2.get(i);
      System.out.println("compare, index: " + i + ", i1: " + i1
              + ", i2: " + i2);
      if (i1 > i2) {
        return 1;
      }
      if (i1 < i2) {
        return -1;
      }
    }
    for (int i = size; i < v1.size(); i++) {
      if (v1.get(i) > 0) {
        return 1;
      }
    }
    for (int i = size; i < v2.size(); i++) {
      if (v2.get(i) > 0) {
        return -1;
      }
    }
    return 0;
  }
}
