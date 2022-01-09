package com.csg.leetcode.algorithm.week02;

import java.util.Arrays;

/**
 * 快排
 * @author zhangxu
 *
 */
public class QuickSort {
	
	public static void main(String[] args) {
		int [] arr = {1,3,6,6,99,4,1,3,7,4,8,6};
		int[] netherlandsFlag = netherlandsFlag(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
		System.out.println("netherlandsFlag:"+Arrays.toString(netherlandsFlag));
	}
	
	public static void  quickSort(int[] arr) {
		process(arr,0,arr.length-1);
	}
	
	private static void process(int[] arr, int L, int R) {
		if(L >= R) {
			return;
		}
		swap(arr,L + (int)Math.random()*(R - L + 1),R);
		int[] side = netherlandsFlag(arr, L, R);
		process(arr,L,side[0]-1);
		process(arr,side[1]+1,R);
	}
	
	

	private static int[] netherlandsFlag(int arr[],int L,int R) {
		if(L > R) {
			return new int[] {-1,-1};
		}
		if(L == R) {
			return new int[] {L, R};
		}
		int less = L-1;
		int bigger = R;
		int index = L;
		while(index < bigger) {
			if(arr[index] == arr[R]) {
				index++;
			}else if(arr[index] < arr[R]){
				swap(arr,index++,++less);
			}else {
				swap(arr,index,--bigger);
			}
		}
		swap(arr, R, bigger);
		return new int[]{less+1,bigger};
	}

	private static void swap(int[] arr, int i, int j) {
		int num = arr[i];
		arr[i] = arr[j];
		arr[j] = num;
	}
}

