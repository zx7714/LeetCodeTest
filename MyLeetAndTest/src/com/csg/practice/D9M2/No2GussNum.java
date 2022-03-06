package com.csg.practice.D9M2;

/**
 * 1~N范围才一个数，每次猜错告诉目标数大于还是小于你猜的数
 * 猜错的代价为你猜的数的值
 * 求猜中的最小代价是
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 */
public class No2GussNum {

    public static int guss(int N) {
        return process(1, N);
    }

    static int process(int L, int R) {
        if (L == R) {
            return 0;
        }
        if (R - L == 1) {
            return L;
        }
        // 选左边
        int left = L + process(L + 1, R);
        // 选右边
        int right = R + process(L, R - 1);
        int min = Math.min(left, right);
        for (int i = L+ 1; i < R;i++) {
            int l = i + process(L, i - 1);
            int r = i + process(i + 1, R);
            int max = Math.max(l, r);
            min = Math.max(max,min);
        }
        return min;
    }

    public static int gussDp(int N) {
        if (N == 0) {
            return N;
        }
        int[][] dp = new int[N+1][N+1];
        for(int i = 1;i < N;i++) {
            dp[i][i+1] = i;
        }
        for (int L = N-2;L > 0 ; L--) {
            for (int R = L + 2; R <= N; R++) {
                int left = L + dp[L + 1][R];
                int right = R + dp[L][R - 1];
                int min = Math.min(left, right);
                for (int i = L+ 1; i < R;i++) {
                    int l = i + dp[L][i - 1];
                    int r = i + dp[i + 1][R];
                    int max = Math.max(l, r);
                    min = Math.max(max,min);
                }
                dp[L][R] = min;
            }
        }
        return dp[1][N];
    }

    public static void main(String[] args) {

        int i = gussDp(7);
        int j = guss(7);
        System.out.println("i == "+ i + " j == " + j);
    }
}
