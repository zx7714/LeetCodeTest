package com.csg.practice.D6M2;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定有0、1组成的2D数组，求全是由1组成的矩形最多含有1的个数
 */
public class No3MaxOneInArray {
    public static int maxOne(int[][] arr) {
        if (null == arr || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
            return 0;
        }
        int N = arr.length;
        int M = arr[0].length;
        int[][] dp = generateDp(arr, N, M);
        print(dp);
        int ans = 0;
        for (int i = 1;i <= N;i++) {
            ans =Math.max(ans,getMaxOne(dp[i],M));
        }
        return ans;
    }

    private static int getMaxOne(int[] dp, int m) {
        Stack<Integer> s = new Stack<>();
        int ans = 0;
        for(int i = 0;i < m;i++) {
            while(!s.empty() && dp[s.peek()] >= dp[i]) {
                int popIndex = s.pop();
                // 需要计算的左边界
                int leftBorder = s.empty() ? 0 : s.peek() + 1;
                ans = Math.max(dp[popIndex] * (i - leftBorder),ans);
            }
            s.push(i);
        }
        while (!s.empty()) {
            int popIndex = s.pop();
            int leftBorder = s.empty() ? 0 : s.peek() + 1;
            ans = Math.max(dp[popIndex] * (m -leftBorder),ans);
        }
        return ans;
    }

    public static int[][] generateDp(int arr[][],int N,int M) {
        int dp[][] = new int[N + 1][M];
        // 第0行弃而不用，省代码
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i-1][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + arr[i-1][j];
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0,1,1,1,1,1},
                {1,1,1,0,1,1},
                {1,1,0,1,1,0}

        };
        int i = maxOne(arr);
        System.out.println(i);
    }
    public static void print(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
