package com.csg.leetcode.camp.topic01_SlidingWindow;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整形num某个arr中的子数组sub，
 * 如果想达标，必须满足sub中最大值-sub中最小值<=num,
 * 返回arr中达标子数组的数量
 * @author zhangxu
 *
 */
public class LessNumSubArray {
	public static int lessNumSub(int[] arr,int num){
		if(arr == null || arr.length ==0){
			return 0;
		}
		//记录当前窗口最大值
		LinkedList<Integer> qMax = new LinkedList<>();
		//记录当前窗口最小值
		LinkedList<Integer> qMin = new LinkedList<>();
		//窗口的L，R
		int L = 0,R=0;
		int res = 0;
		while(L < arr.length){
			while(R < arr.length){
				//从小到大
				while(!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]){
					qMin.pollLast();
				}
				qMin.addLast(R);
				//从大到小
				while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]){
					qMax.pollLast();
				}
				qMax.addLast(R);
				//当前L...R窗口中第一个违规的值
				if(arr[qMax.getFirst()] - arr[qMin.getFirst()] > num){
					break;
				}
				R++;
			}
			//R表示第一个违规的值，R-L表示在这个窗口下，所有达标的值
			res += R - L;
			//移动窗口的左边界
			if(qMin.peekFirst() == L){
				qMin.pollFirst();
			}
			if(qMax.peekFirst() == L){
				qMax.pollFirst();
			}
			L++;
		}
		
		return res;
	}
	
	public static int lessNumSub_rep(int arr[],int num){
		if(null == arr || arr.length ==0){
			return 0;
		}
		LinkedList<Integer> qMax = new LinkedList<>();
		LinkedList<Integer> qMin = new LinkedList<>();
		int L = 0; //窗口左边界
		int R = 0; //窗口右边界
		int ans = 0;
		while(L < arr.length){
			while(R < arr.length){
				
				while(!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]){
					qMin.pollLast();
				}
				qMin.addLast(R);
				
				while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]){
					qMax.pollLast();
				}
				qMax.addLast(R);
				
				
				if(arr[qMax.getFirst()] - arr[qMin.getFirst()] > num){
					break;
				}
				R++;
			}
			ans += R - L;
			
			if(qMax.peekFirst() == L){
				qMax.pollFirst();
			}
			if(qMin.peekFirst() == L){
				qMin.pollFirst();
			}
			L++;
		}
		
		return ans;
	}
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		System.out.println(lessNumSub_rep(arr,1));
	}
}
