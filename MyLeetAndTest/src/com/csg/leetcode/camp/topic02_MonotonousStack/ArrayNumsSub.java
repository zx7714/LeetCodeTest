package com.csg.leetcode.camp.topic02_MonotonousStack;

import java.util.Stack;

/**
 * 	给定一个只包含正整数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出（sub累加和）*（sub中最小值是什么），
 * 那么所有子数组中，这个值最大是多少？
 * @author zhangxu
 *
 */
public class ArrayNumsSub {
	public static int sub(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		//构造累加和数组
		int sum[] = new int[arr.length];
		sum[0] = arr[0];
		for(int i = 1;i < arr.length;i++){
			sum[i] = sum[i-1] + arr[i]; 
		}
		
		int max = Integer.MIN_VALUE;
		
		Stack<Integer> s = new Stack<>();
		// 从小到大，找到左边离他最近比他小的L，找到右边离他最近比他小R
		// L...R这个范围就是栈顶元素为最小值的子数组
		
		for(int i = 0;i < arr.length;i++){
			while(!s.isEmpty() && arr[s.peek()] >= arr[i]){
				int popIndex = s.pop();
				//计算最大值
				max = Math.max(max, (s.isEmpty() ? sum[i-1]  : sum[i-1] - sum[s.peek()]) * arr[popIndex]);
			}
			s.push(i);
		}
		while(!s.isEmpty()){
			int popIndex = s.pop();
			max = Math.max(max, (s.isEmpty() ? sum[arr.length - 1] : sum[arr.length - 1] - sum[s.peek()]) * arr[popIndex]);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5};
		System.out.println(sub(arr));
	}
}
