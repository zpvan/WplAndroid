package com.knox.leetcode.sort;

public class MyQuickSort {

    public void sort(int[] nums) {
        _sort(nums, 0, nums.length - 1);
    }

    // 递归调用函数
    private void _sort(int[] nums, int l, int r) {

        if (l >= r) return;

        // 获取分区点
        int q = _partition(nums, l, r);
        _sort(nums, l, q);
        _sort(nums, q + 1, r);
    }

    private int _partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                _swap(nums, i, j);
                i++;
            }
        }
        _swap(nums, i, r);
        return i;
    }

    private void _swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
