package com.knox.leetcode.number;

import org.junit.Test;

import java.util.Arrays;

public class Test1089 {

	@Test
	public void test() {
		int[] arr = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
		duplicateZeros(arr);
		System.out.println("ans: " + Arrays.toString(arr));
		arr = new int[]{1, 2, 3};
		duplicateZeros(arr);
		System.out.println("ans: " + Arrays.toString(arr));
	}

	public void duplicateZeros(int[] arr) {
		int[] _arr = arr.clone();
		for (int i = 0, j = 0; i < arr.length && j < arr.length; i++) {
			arr[j++] = _arr[i];
			if (_arr[i] == 0 && j < arr.length) {
				arr[j++] = 0;
			}
		}
	}
}
