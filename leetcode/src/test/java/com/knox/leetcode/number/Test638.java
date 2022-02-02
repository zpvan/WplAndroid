package com.knox.leetcode.number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test638 {

	Map<List<Integer>, Integer> mem = new HashMap<>();

	@Test
	public void test() {

	}

	public int shoppingOffers(List<Integer> price,
							  List<List<Integer>> special,
							  List<Integer> needs) {
		// 一共有多少种商品
		int n = price.size();

		// 过滤出真正有优惠的大礼包
		List<List<Integer>> realSpecial = new ArrayList<>();
		for (List<Integer> sp : special) {
			int totalPrice = 0;
			for (int i = 0; i < n; i++) {
				// 记录大礼包里边的商品如果用原价买, 要多少钱
				totalPrice += sp.get(i) * price.get(i);
			}
			if (totalPrice > sp.get(n)) {
				realSpecial.add(sp);
			}
		}
		return _dfs(price, realSpecial, needs, n);
	}

	private int _dfs(List<Integer> price,
					 List<List<Integer>> special,
					 List<Integer> needs,
					 int n) {
		if (!mem.containsKey(needs)) {
			int minPrice = 0;
			for (int i = 0; i < n; i++) {
				// 全部按原价买
				minPrice += needs.get(i) * price.get(i);
			}
			for (List<Integer> sp : special) {
				List<Integer> nextNeeds = new ArrayList<>();
				// 该大礼包的总价
				int specialPrice = sp.get(n);
				for (int i = 0; i < n; i++) {
					if (sp.get(i) > needs.get(i)) {
						// 不能购买该大礼包, 因为商品数量超了
						break;
					}
					nextNeeds.add(needs.get(i) - sp.get(i));
				}
				if (nextNeeds.size() == n) {
					// 上面for循环没有break
					minPrice = Math.min(minPrice, _dfs(price, special, nextNeeds, n) + sp.get(n));
				}
			}
			mem.put(needs, minPrice);
		}
		return mem.get(needs);
	}
}
