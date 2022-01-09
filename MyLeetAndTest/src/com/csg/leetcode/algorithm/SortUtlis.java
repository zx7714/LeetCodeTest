package com.csg.leetcode.algorithm;

import java.util.Arrays;

import com.csg.leetcode.algorithm.week02.QuickSort;

public class SortUtlis {
	
	private static final int maxLength = 100;
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
	
	
	private static boolean checkNum(int arr[]) {
		
		int arr2[] = Arrays.copyOf(arr, arr.length);
		
		if(!checkArrayIfComplete(arr,arr2)) 
			return false;
		
		Arrays.sort(arr);
		
		//自定义排序
		//InsertedSort.insertedSort(arr2);
		//MergeSort.mergeSort2(arr2);
		
		QuickSort.quickSort(arr2);
		if(!checkArrayIfComplete(arr,arr2)) 
			return false;
		
		return true;
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


	public static void swap(int[] arr,int i,int j) {
		int index = arr[i];
		arr[i] = arr[j];
		arr[j] = index;
	}
}
