package com.csg.leetcode.algorithm.week02;

import java.util.Arrays;
//归并排序
public class MergeSort {
	
	
	public static void mergeSort(int[] nums) {
		if(nums == null || nums.length < 2) {
			return;
		}
		process(nums,0,nums.length-1);
	}
	public static void process(int[] nums, int L, int R) {
		if(L == R) {
			return ;
		}
		int mid = L+ ((R-L) >> 1);
		process(nums, L, mid);
		process(nums, mid+1, R);
		merge(nums,L,mid,R);
	}
	private static void merge(int[] nums, int L, int M, int R) {
		int[] help = new int[R-L+1];
		int i = 0;//指向help数组的数
		int index1 = L;//左边区域的指针
		int index2 = M+1;//右边区域的指针
		while(index1 <= M && index2 <= R) {
			help[i++] = nums[index1] <= nums[index2] ? nums[index1++] : nums[index2++]; 
		}
		while(index1 <= M) {
			help[i++] = nums[index1++];
		}
		while(index2 <= R) {
			help[i++] = nums[index2++];
		}
		//将help数组中的数复制到原数组
		for(i = 0 ;i<help.length;i++) {
			nums[L+i] = help[i];
		}
		
	}
	
	public static void main(String[] args) {
		int[] nums = {193754, -154722, -97215, -194178, 206925};
		mergeSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
	
	
	public static void mergeSort2(int[] nums) {
		if(null == nums || nums.length < 2) {
			return ;
		}
		process2(nums,0,nums.length-1);
	}
	private static void process2(int[] nums, int L, int R) {
		if(L == R) {
			return;
		}
		int mid = L+((R-L)>>2);
		process2(nums, L, mid);
		process2(nums, mid+1,R);
		merge2(nums,L,mid,R);
	}
	private static void merge2(int[] nums, int l, int mid, int r) {
		int help[] = new int[r-l+1];
		int i = 0;
		int p1 = l;
		int p2 = mid + 1;
		while(p1<=mid && p2 <= r) {
			help[i++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
		}
		while(p1 <= mid) {
			help[i++] = nums[p1++];
		}
		while(p2 <= r) {
			help[i++] = nums[p2++];
		}
		for(i=0;i<help.length;i++) {
			nums[l+i] = help[i];
		}
	}
	
	
	
}
