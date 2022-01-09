package com.csg.leetcode.algorithm.week02;

import java.util.Arrays;

/**
 * 小和
 * 数组的小和，一个数左边比他小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和
 * @author zhangxu
 *
 */
public class MinSumOfArray {
	
	private static final int maxLength = 5;
	private static final int roundTime = 500000;
	private static final int numMax = 500000;
	
	public static void main(String[] args) {
		boolean flag = true;
		for (int i = 0; i < roundTime; i++) {
			flag = initSortAlg();
			if(!flag) break;
		}
		System.out.println(flag ? "nice!" : "Oops!");
	}
	
	public static boolean initSortAlg() {
		int[] arr = new int[(int) (Math.random()*maxLength)+1];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * numMax - Math.random() * (numMax-1));
		}
		//System.out.println(Arrays.toString(arr));
		return checkNum(arr);
	}
	
	
	private static boolean checkNum(int[] arr) {
		int arr2[] = Arrays.copyOf(arr, arr.length);
		if(!checkArrayIfComplete(arr,arr2)) 
			return false;
		int minSumFor = getMinSumFor(arr);
		int minSum = getMinSum(arr2,0,arr2.length-1);
		if( minSumFor==minSum ) {
			return true;
		}
		return false;
	}

	private static boolean checkArrayIfComplete(int[] arr, int[] arr2) {
		if( arr.length ==0 || arr2.length == 0 
				|| arr.length != arr2.length)
			return false;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != arr2[i]) return false;
		}
		return true;
	}

	public static int getMinSumFor(int[] nums){
		int result = 0;
		if(nums == null || nums.length < 2) {
			return result;
		}
		
		for(int i = 1;i<nums.length;i++) {
			for(int j = i-1;j>=0;j--) {
				result += nums[i] > nums[j] ? nums[j] : 0;
			}
		}
		
		return result;
		
	}
	
	public static int getMinSum(int[] nums,int L,int R){
		if(nums == null || nums.length < 2) {
			return 0;
		}
		return process(nums,L,R);
	}

	private static int process(int[] nums, int L, int R) {
		if(L == R) {
			return 0;
		}
		int M = L+((R-L) >> 1);
		return process(nums, L, M) 
				+ process(nums, M+1, R)
				+ merge(nums,L,M,R);
	}

	private static int merge(int[] nums, int L, int M, int R) {
		int result = 0;
		int help[] = new int[R-L+1];
		int i = 0;
		int p1 = L;
		int p2 = M+1;
		while(p1<=M && p2 <=R) {
			result += nums[p1] < nums[p2] ? (R - p2 + 1) * nums[p1] : 0;
			help[i++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
		}
		while(p1 <= M) {
			help[i++] = nums[p1++];
		}
		while(p2 <= R) {
			help[i++] = nums[p2++];
		}
		for(i=0;i<help.length;i++) {
			nums[L+i] = help[i]; 
		}
		return result;
	}

}
