package com.csg.leetcode.leet;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class Hot53MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int dp = nums[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1 ; i < nums.length;i++) {
            dp = Math.max(dp + nums[i],nums[i]);
            max = Math.max(dp,max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }
}
