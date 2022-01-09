package com.csg.leetcode.algorithm.week01;
/**
 * 在一个数组中，有两个数重复次数为基数次，其他均为偶数次。找出这两个数
 * @author zhangxu
 *
 */
public class FindTwoUnrepeatNum {
	public static void main(String[] args) {
		int [] arr = {1,1,2,2,3,3,3,4,4,4,5,5};
		find(arr);
	}
	public static void find(int[] nums) {
		int target = 0;
		for (int i = 0; i < nums.length; i++) {
			target ^=nums[i];
		}
		//取反+1可以算出一个数的二进制数最右边的一个位置
		int digt  = target & (~target + 1);
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if((nums[i] & digt) == 0)
				res ^= nums[i];
		}
		System.out.println("num1="+res+",num2=" +  (target^res) );
	}
}
