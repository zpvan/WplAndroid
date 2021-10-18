package com.knox.leetcode.number;

import java.util.Stack;

class Test901 {

}

class StockSpanner {

    Stack<Pair<Integer, Integer>> s = new Stack<>();

    public StockSpanner() {

    }

    public int next(int price) {
        int w = 1;

        while (!s.isEmpty() && s.peek().key < price) {
            w += s.pop().value;
        }

        s.push(new Pair<>(price, w));

        return w;
    }

    static class Pair<K, V> {
        public final K key;
        public final V value;

        public Pair(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }
}

