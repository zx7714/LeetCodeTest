package com.csg.leetcode.leet;

/**
 * https://leetcode-cn.com/problems/0i0mDW/
 */
public class Offer99MinDistance {
    public static int minPathSum(int[][] grid) {
        if (null == grid || grid.length == 0) {
            return -1;
        }
        int N = grid.length;
        int M = grid[0].length;
        int dp[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int cur = grid[i][j];
                int minLeft = Integer.MAX_VALUE;
                int minUp = Integer.MAX_VALUE;
                if (i - 1 >= 0) {
                    minUp = dp[i - 1][j] + cur;
                }
                if (j - 1 >= 0) {
                    minLeft = dp[i][j - 1] + cur;
                }
                dp[i][j] = Math.min(dp[i][j], Math.min(minLeft, minUp));
            }
        }
        return dp[N - 1][M - 1];
    }

    public static int minPathSum2(int[][] grid) {
        if (null == grid || grid.length == 0) {
            return -1;
        }
        int N = grid.length;
        int M = grid.length;
        int[] dp = new int[M];
        dp[0] = grid[0][0];
        for (int i = 1; i < M; i++) {
            dp[i] = grid[0][i] + dp[i - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int minUp = dp[j] + grid[i][j];
                int minLeft = j - 1 >= 0 ? dp[j -1] + grid[i][j] : Integer.MAX_VALUE;
                dp[j] = Math.min(minLeft, minUp);
            }
        }
        return dp[M - 1];
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum2(arr));
    }
}
