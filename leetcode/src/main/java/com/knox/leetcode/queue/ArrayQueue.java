package com.knox.leetcode.queue;

// 用数组实现的队列
public class ArrayQueue {
    private String[] items;     // 数组
    private int      n    = 0;  // 队列的大小
    private int      head = 0;  // 队头下标
    private int      tail = 0;  // 队尾下标

    // 初始化数组，申请一个大小为n的数组空间
    public ArrayQueue(int n) {
        this.items = new String[n];
        this.n = n;
    }

    // 入队
    public boolean enqueue(String item) {
        if (tail == n) {
            if (head == 0) {
                // 如果tail == n && head == 0，表示队列已经满了
                return false;
            }
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                items[i - head] = items[i];
            }
            // 搬移完之后重新更新head和tail
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果head == tail，表示队列已经空了
        if (head == tail) {
            return null;
        }
        String temp = items[tail];
        ++head;
        return temp;
    }
}
