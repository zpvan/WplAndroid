package com.knox.leetcode.queue;

public class CircularQueue {
    private String[] items;     // 数组
    private int      n    = 0;  // 队列的大小
    private int      head = 0;  // 队头下标
    private int      tail = 0;  // 队尾下标

    // 初始化数组，申请一个大小为n的数组空间
    public CircularQueue(int n) {
        this.items = new String[n];
        this.n = n;
    }

    // 入队
    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue() {
        // 队列空了
        if (tail == head) {
            return null;
        }
        String temp = items[head];
        head = (head + 1) % n;
        return temp;
    }
}
