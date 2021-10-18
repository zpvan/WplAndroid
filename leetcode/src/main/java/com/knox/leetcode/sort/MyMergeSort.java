package com.knox.leetcode.sort;

/**
 * 递推公式：merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))
 * 终止条件：p >= r 不用再继续分解
 */
public class MyMergeSort {

    public void sort(int[] nums) {
        _sort(nums, 0, nums.length - 1);
    }

    // 递归调用函数
    private void _sort(int[] nums, int l, int r) {
        // 递归终止条件
        if (l >= r) return;

        // 取l到r之间的中间位置m
        int m = (l + r) / 2;
        _sort(nums, l, m);
        _sort(nums, m + 1, r);
        // 将nums[l..m]与nums[m+1..r]合并为nums[l..r]
        _merge(nums, l, r, l, m, m + 1, r);
    }

    private void _merge(int[] nums, int l, int r, int l1, int r1, int l2, int r2) {
        // 初始化变量i, j, k
        int i1 = l1;
        int i2 = l2;
        int k = 0;
        // 申请一个大小跟nums[l...r]一样的临时数组
        int[] temp = new int[r - l + 1];
        while (i1 <= r1 && i2 <= r2) {
            if (nums[i1] < nums[i2]) {
                temp[k++] = nums[i1++];
            } else {
                temp[k++] = nums[i2++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i1, end = r1;
        if (i2 < r2) {
            start = i2;
            end = r2;
        }

        // 将剩余的数据拷贝到临时数组temp
        while (start <= end) {
            temp[k++] = nums[start];
        }

        // 将temp中的数组拷贝回nums[l...r]
        System.arraycopy(temp, 0, nums, l, r - l + 1);
    }
}
