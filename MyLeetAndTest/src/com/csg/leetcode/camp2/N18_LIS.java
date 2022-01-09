package com.csg.leetcode.camp2;

import java.util.Arrays;
/**
 * 最长递增子序列问题的O(N*logN)的解法
 * @author zhangxu
 *
 */
public class N18_LIS {
	public static int[] lis(int[] arr){
		if(null == arr || arr.length == 0 ){
			return null;
		}
		int dp[] = getDp(arr);
		int ans[] = generateLis(arr,dp);
		return ans;
	}

	private static int[] generateLis(int[] arr,int[] dp) {
		int index = 0;
		int maxLen = 0;
		//最长子数组的长度和下标
		for(int i = 0; i < dp.length; i++){
			if(dp[i] > maxLen){
				maxLen = dp[i];
				index = i;
			}
		}
		//填充ans
		int[] ans = new int[maxLen];
		//最后的值
		ans[--maxLen] =  arr[index];
		for(int i = index; i >= 0;i--){
			// [i]值小于index位置的值。 dp[i]的值与dp[index]的值差1
			if(arr[i] < arr[index] && dp[i] == dp[index] -1){
				//填充ans
				ans[--maxLen] = arr[i];
				//index跳到满足条件的下一个值
				index = i;
			}
		}
		
		return ans;
	}

	private static int[] getDp(int[] arr) {
		// 以当前位置结尾的最长有序子序列
		int dp[] = new int[arr.length]; 
		//辅助数组，记录当前[i]结尾,前面有多少比他小的,有序的
		int ends[] = new int[arr.length];
		ends[0] = arr[0];
		dp[0] = 1;
		int right = 0; //当前有效边界
		int l = 0; 
		int r = 0;
		int m = 0; //中间值
		//填充dp值
		for(int i = 1;i < arr.length;i++){
			l = 0;
			r = right;
			//二分查找，更新end，看arr[i]前面比他小的数
			while(l <= r){
				m = l - ((l-r) >> 2);
				if(arr[i] > ends[m]){
					l = m + 1;
				}else{
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}
	
	public static void main(String[] args) {
		int[] arr = { 1,2, 1, 5, 3, 6, 4, 8, 9, 7 };
		int[] dp = getDp(arr);
		System.out.println(Arrays.toString(dp));
		System.out.println(Arrays.toString(lis(arr)));
	}
}
