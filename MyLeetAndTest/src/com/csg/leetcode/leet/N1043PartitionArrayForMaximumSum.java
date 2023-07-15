package com.csg.leetcode.leet;

/**
 * https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
 */
public class N1043PartitionArrayForMaximumSum {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int N = arr.length;
        int[] dp = new int[N];
        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            int max = arr[i];
            // i 位置自己一组
            dp[i] = arr[i] + dp[i-1];
            for (int j = i - 1; i - j  < k && j >= 0 ; j--) {
                // k个一组中的最大值
                max = Math.max(max,arr[j]);
                dp[i] = Math.max(dp[i],max * (i - j + 1) + (j - 1 >= 0 ? dp[j -1] : 0));
            }
        }
        return dp[N - 1];
    }
}
