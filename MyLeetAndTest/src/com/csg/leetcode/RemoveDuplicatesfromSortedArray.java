package com.csg.leetcode;

import java.util.Arrays;

public class RemoveDuplicatesfromSortedArray {
	public static void main(String args[]){
		int[] nums = {0,0,0,1,1,2,2,3,3,4};
		new RemoveDuplicatesfromSortedArray().removeDuplicatesfromSortedArrayMineTwoPoint(nums);
	}
	/**
	 * 双指针法，一个快的指针，一个慢的指针
	 * @param nums
	 * @return
	 */
	public int removeDuplicatesfromSortedArrayMineTwoPoint(int nums[]){
		int i = 0;
		for(int j=1;j<nums.length;j++){
			if(nums[i]!=nums[j]){
				i++;
				nums[i] = nums[j];
			}
		}
		System.out.println(Arrays.toString(nums));
		return i+1;
	}
}
