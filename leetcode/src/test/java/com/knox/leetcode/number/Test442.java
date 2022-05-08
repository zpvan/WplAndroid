package com.knox.leetcode.number;

import java.util.ArrayList;
import java.util.List;

public class Test442 {

	public List<Integer> findDuplicates(int[] nums) {
		/**
		 * 特殊技巧
		 * 第一次遍历：预期每个数字都要放到对应的位置
		 * 第二次遍历：每个位置上不符合预期的数字都是有重复的数字
		 *
		 * 初始化：3、3、4、2
		 * 第一轮：4、3、3、2 （3跟4换）
		 * 第二轮：2、3、3、4 （4跟2换）
		 * 第三轮：3、2、3、4 （2跟3换）
		 * 第四轮：3、2、3、4 （3跟3相等，不用换）
		 */
		for (int i = 0; i < nums.length; i++) {
			while (nums[nums[i] - 1] != nums[i]) {
				swap(nums, nums[i] - 1, i);
			}
		}
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] - 1 != i) {
				ans.add(nums[i]);
			}
		}
		return ans;
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
