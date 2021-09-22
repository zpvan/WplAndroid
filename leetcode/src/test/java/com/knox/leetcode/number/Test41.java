package com.knox.leetcode.number;

import java.util.Arrays;

class Test41 {
  public int firstMissingPositive(int[] A) {
    int i = 0;
    // 保证索引有效
    while (i < A.length) {
      // 数值一定出现在A.length里边, 所以i从0开始数,
      if (A[i] == i + 1 // 预期中[1,2,3,...]，前面顺序的数值都可以忽略
          || A[i] <= 0 // 如果中间碰到<=0的数值，也忽略
          || A[i] > A.length) // 如果中间>A.length的数值，也忽略
      {
        i++;
      } else if (A[A[i] - 1] != A[i]) // 当
      {
        swap(A, i, A[i] - 1);
      }
      else i++;
    }
    System.out.println("done: " + Arrays.toString(A));
    i = 0;

    while (i < A.length && A[i] == i + 1) i++;
    return i + 1;
  }

  private void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
}
