package com.csg.leetcode.camp2;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部份，剩下的作为有部分
 * 但是每种划分下都有左部份的最大值和右部分的最大值，请返回最大的，左部分最大值减去左部分最大值的绝对值。
 * 
 * @author zhangxu
 *
 */
public class N8_TwoPartMaxSubstraction {
	/*
	 * 找到最大值，然后减去arr[0]和arr[length-1]的最小值，
	 * 因为不可能每部分的最大值会比两个边界的值小
	 */
	public static int sub(int arr[]){
		if(arr == null || arr.length < 1){
			return 0;
		}
		int max = arr[0];
		for(int i = 1;i<arr.length;i++){
			max = Math.max(max, arr[i]);
		}
		
		return Math.abs(max - Math.min(arr[0], arr[arr.length-1]));
		
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		System.out.println(sub(arr));
	}
}
