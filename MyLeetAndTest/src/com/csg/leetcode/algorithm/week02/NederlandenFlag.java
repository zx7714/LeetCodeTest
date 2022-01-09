package com.csg.leetcode.algorithm.week02;

import java.util.Arrays;
/**
 * 荷兰国旗问题
 * @author zhangxu
 *
 */
public class NederlandenFlag {
	
	public static void main(String[] args) {
		int [] arr = {1,3,6,6,99,4,1,3,7,4,8,6};
		nederlandenFlag(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	/*
	 * 1,[i] == num, index++
	 * 2,[i] > num, [i]与>区左一个交换,>区左移,index不懂
	 * 3,[i] < num, [i]与<区右一个交换,<区右移,index++
	 */
	public static void nederlandenFlag(int[] arr,int L,int R) {
		int less = L-1;
		int bigger = R;
		int index = L;
		while(index < bigger) {
			if(arr[index] == arr[R]) {
				index++;
			}else if(arr[index] < arr[R]) {
				swap(arr,index++,++less);
			}else {
				swap(arr,index,--bigger);
			}
		}
		swap(arr,R,bigger);
		
	}

	private static void swap(int[] arr, int i, int j) {
		int num = arr[i];
		arr[i] = arr[j];
		arr[j] = num;
	}
}
