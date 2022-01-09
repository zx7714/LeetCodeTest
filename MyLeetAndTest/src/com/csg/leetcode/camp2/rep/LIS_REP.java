package com.csg.leetcode.camp2.rep;

import java.util.Arrays;

public class LIS_REP {
	public static int[] lis(int arr[]){
		int dp[] = generateDp(arr);
		int ans[] = generateLis(arr,dp);
		return ans;
	}

	private static int[] generateLis(int[] arr, int[] dp) {
		int maxLen = Integer.MIN_VALUE;
		int lenIndex = 0;
		for(int i = 0 ; i < dp.length;i++){
			if(arr[i] > maxLen){
				maxLen = dp[i];
				lenIndex = i;
			}
		}
		
		int ans[] = new int[maxLen];
		ans[--maxLen] = arr[lenIndex];
		for(int i = lenIndex; i >=0 ; i--){
			if(arr[i] < arr[lenIndex] && dp[lenIndex] == dp[i] + 1){
				ans[--maxLen] = arr[i];
				lenIndex = i;
			}
		}
		return ans;
	}

	private static int[] generateDp(int[] arr) {
		int[] dp = new int[arr.length];
		int[] ends = new int[arr.length];
		int right = 0; //可用范围
		int l = 0; 
		int r = 0;
		int m = 0;
		dp[0] = 1;
		ends[0] = arr[0];
		for(int i = 1; i < arr.length;i++){
			l = 0;
			r = right;
			while(l <= r){
				m = r - ((r - l) >> 2);
				if(arr[i] > ends[m]){
					l = m + 1;
				}else{
					r = m -1;
				}
			}
			right = Math.max(l, right);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{10,9,2,5,3,7,101,18};
		System.out.println(Arrays.toString(lis(arr)));
	}
}
