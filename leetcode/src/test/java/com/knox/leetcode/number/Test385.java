package com.knox.leetcode.number;

import org.junit.Test;

import java.util.List;



public class Test385 {

	@Test
	public void test() {
		deserialize("[123,[456,[789]]]");
	}

	public NestedInteger deserialize(String s) {
        NestedInteger ans = null;

        assert ans != null;
		int _pos = s.indexOf('[');
		int pos_ = s.indexOf(',');
		if (pos_ != -1) {
			Integer num = Integer.valueOf(s.substring(_pos + 1, pos_));
			System.out.println("num1: " + num);
            ans.setInteger(num);
            ans.add(deserialize(s.substring(pos_ + 1)));
		} else {
			int pos__ = s.indexOf(']');
			if (pos__ != -1) {
				Integer num = Integer.valueOf(s.substring(_pos + 1, pos__));
				System.out.println("num2: " + num);
                ans.setInteger(num);
			} else {
				Integer num = Integer.valueOf(s.substring(_pos + 1));
				System.out.println("num3: " + num);
                ans.setInteger(num);
			}
		}
		return ans;
	}

    public interface NestedInteger {
        // Constructor initializes an empty nested list.
//        public NestedInteger();

        // Constructor initializes a single integer.
//        public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}