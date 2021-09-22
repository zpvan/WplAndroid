package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test15 {

  @Test
  public List<List<Integer>> threeSum(int[] nums) {
    // nums = Arrays.stream(nums).sorted().toArray();
    // mergeSorted(nums, 0, nums.length - 1);
    quickSorted(nums, 0, nums.length - 1);
    // Arrays.sort(nums);
    Set<List<Integer>> ans = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (sum == 0) {
          ans.add(List.of(nums[i], nums[j], nums[k]));
          j++;
          k--;
          continue;
        }
        if (sum > 0) {
          k--;
        } else {
          j--;
        }
      }
    }
    return new ArrayList<>(ans);
  }

  private void quickSorted(int[] nums, int l, int r) {
    // time  ==> O(nlogn)
    // space ==> 0

    // recursive function: quickSorted(nums, l, p - 1) + quickSorted(nums, p, r)
    // end condition: l >= r
    if (l >= r) return;

    int q = partition(nums, l, r);
    quickSorted(nums, l, q - 1);
    quickSorted(nums, q, r);
  }

  private int partition(int[] nums, int l, int r) {
    int pivot = nums[r];
    int i = l;
    for (int j = l; j < r; j++) {
      if (nums[j] < pivot) {
        swap(nums, i, j);
        i++;
      }
    }
    swap(nums, i, r);
    return i;
  }

  private void swap(int[] nums, int a, int b) {
    int temp = nums[b];
    nums[b] = nums[a];
    nums[a] = temp;
  }

  private void mergeSorted(int[] nums, int l, int r) {
    // time  ==> O(nlogn)
    // space ==> O(n)

    // recursive function: mergeSorted(nums, l, r) = merge(mergeSorted(nums, l, p),
    // mergeSorted(nums, p+1, r))
    // end condition:      l >= r
    if (l >= r) return;

    int p = (l + r) / 2;
    mergeSorted(nums, l, p);
    mergeSorted(nums, p + 1, r);

    merge(nums, l, r, nums, l, p, nums, p + 1, r);
  }

  private void merge(
      int[] out, int o_l, int o_r, int[] in1, int i1_l, int i1_r, int[] in2, int i2_l, int i2_r) {
    int[] temp = new int[o_r - o_l + 1];
    int i = i1_l, j = i2_l, k = 0;
    while (i <= i1_r && j <= i2_r) {
      if (in1[i] <= in2[j]) {
        temp[k++] = in1[i++];
      } else {
        temp[k++] = in2[j++];
      }
    }

    if (i <= i1_r) {
      while (i <= i1_r) {
        temp[k++] = in1[i++];
      }
    } else {
      while (j <= i2_r) {
        temp[k++] = in2[j++];
      }
    }

    System.arraycopy(temp, 0, out, o_l, temp.length);
  }
}
