package com.knox.leetcode.tree;

// 大顶堆
public class Heap {
    // 数组，从下标1开始存储数据
    private int[] a;
    // 堆可以存储的最大数据个数
    private int   n;
    // 堆已经存储的数据个数
    private int   count;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) {
            // 堆满了
            return;
        }
        ++count;
        a[count] = data;
        // heapify 从下到上堆化
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            // swap
            int temp = a[i];
            a[i] = a[i / 2];
            a[i / 2] = temp;

            i = i / 2;
        }
    }

    public void removeTop() {
        if (count == 0) {
            // 堆中没有数据
            return;
        }
        // 删除堆顶元素，且用最后一个元素当做堆顶
        a[1] = a[count];
        --count;
        // heapify 从上到下堆化
        int i = 1;
        while (true) {
            // 根节点，左子节点，右子节点比较谁更大
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                // 不需要交换，即堆化完成
                break;
            }
            // 交换
            int temp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = temp;
            // 下一次堆化的根节点
            i = maxPos;
        }
    }

    // 原地建堆
    public static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; i--) {
            // 从上往下堆化，
            // 从第一个非叶子节点开始，保证每个子树都是堆化好的，
            // 最后能才从上往下堆化
            heapify(a, n, i);
        }
    }

    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2])
                maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1])
                maxPos = i * 2 + 1;
            if (maxPos == i)
                break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private static void swap(int[] a, int i, int maxPos) {
        int temp = a[i];
        a[i] = a[maxPos];
        a[maxPos] = temp;
    }

    public static void sort(int[] a, int n) {
        // n表示数据的个数，数组a中的数据从下标1到n的位置
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }
}
