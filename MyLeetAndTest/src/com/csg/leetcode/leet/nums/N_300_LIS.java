package com.csg.leetcode.leet.nums;
/**
 *  给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

	子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
	例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
	 
	示例 1：
	
	输入：nums = [10,9,2,5,3,7,101,18]
	输出：4
	解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
	示例 2：
	
	输入：nums = [0,1,0,3,2,3]
	输出：4
	示例 3：
	
	输入：nums = [7,7,7,7,7,7,7]
	输出：1
	
 * @author zhangxu
 *
 */
public class N_300_LIS {
	public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = getDp(nums);
        int ans = dp[0];
        for(int i = 0;i < dp.length;i++){
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }


    public static int[] getDp(int[] arr){
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        dp[0] = 1;
        ends[0] = arr[0];

        for(int i = 1;i < arr.length;i++){
            l = 0;
            r = right;
            while(l <= r){
            	m = l - ((l - r ) >> 2);
                if(arr[i] > ends[m]){
                    l = m + 1;
                }else {
                    r = m -1;
                }
            }
            right = Math.max(l,right);
            ends[l] = arr[i]; 
            dp[i] = l + 1;
//        	l = 0;
//            r = right;
//            while(l <= r){
//                m = (r + l )/ 2;
//                if(ends[m] < arr[i]){
//                    l = m + 1;
//                }else {
//                    r = m -1;
//                }
//                right = Math.max(l,right);
//                ends[l] = arr[i]; 
//                dp[i] = l + 1;
//            }
        }
        return dp;
    }
    
    public static void main(String[] args) {
		int arr[] = new int[]{10,9,2,5,3,4};
		int ans = lengthOfLIS(arr);
		System.out.println(ans);
    }
}
