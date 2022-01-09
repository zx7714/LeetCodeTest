package com.csg.leetcode.leet.nums;

import java.util.ArrayList;
import java.util.List;


/**
 * 数组全排列
 * @author zhangxu
 *
 */
public class N_47_PermutationsII {
	
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> use = new ArrayList<>();
		int idx = 0;
		process(nums,idx,ans, use);
		ans.forEach(a ->{
			System.out.println(a);
		});
		System.out.println();
		return ans;
    }

	private static void process(int[] nums, 
			int idx,
			List<List<Integer>> ans, 
			List<Integer> use) {
		
		if(use.size() == nums.length) {
			ans.add(use);
		}
		for(int i = 0; i < nums.length ;i++) {
			if(!use.contains(nums[i])) {
				use.add(nums[i]);
				process(nums, idx+1,ans, use);
				use.remove(idx);
			}
		}
		
	}

	public static void main(String[] args) {
		int[] i = new int[]{1,2,3};
		permuteUnique(i);
	}
}
