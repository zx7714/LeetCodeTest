package com.csg.leetcode.camp.topic01_SlidingWindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 	假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值。
 *  例如，arr=[4,3,5,4,3,3,6,7],W=3
 *  返回：[5,5,5,4,6,7]
 *  @author zhangxu
 *
 */
public class MaxNumInWindow {
	public static int[] maxNum(int nums[],int w){
		if(nums == null || w < 1|| nums.length < w){
			return null;
		}
		//ans
		int[] ans = new int[nums.length-w+1];
		LinkedList<Integer> q = new LinkedList<>();
		int index = 0;
		for(int r = 0;r < nums.length;r++){
			//双端队列，保证里面的值是由大到小
			while(!q.isEmpty() &&  nums[q.peekFirst()] < nums[r]){
				q.pollFirst();
			}
			q.addLast(r);
			//窗口左侧的值滑出窗口
			if(q.peekFirst() == r-w){
				q.pollFirst();
			}
			//达到了窗口的值
			if(r >= w - 1){
				//双端队列第一个值就是当前窗口最大的值。
				ans[index++] = nums[q.peekFirst()];
			}
		}
		
		return ans;
	}
	
	public static int[] maxNum_rep(int arr[],int w){
		if(null == arr || w < 1 || arr.length < w){
			return null;
		}
		int ans[] = new int[arr.length - w + 1];
		LinkedList<Integer> q = new LinkedList<>();
		int index = 0;
		for(int i = 0;i < arr.length;i++){
			while(!q.isEmpty() && arr[q.peekLast()] < arr[i]){
				q.pollFirst();
			}
			q.addLast(i);
			
			if(q.peekFirst() == i - w){
				q.pollFirst();
			}
			if(i >= w - 1){
				ans[index++] = arr[q.peekFirst()];
 			}
		}
		
		
		return ans;
	}
	
	public static void main(String[] args) {
		//arr=[4,3,5,4,3,3,6,7],W=3
		int arr[] = {4,3,5,4,3,3,6,7};
		System.out.println(Arrays.toString(maxNum_rep(arr, 3)));
	}
}
