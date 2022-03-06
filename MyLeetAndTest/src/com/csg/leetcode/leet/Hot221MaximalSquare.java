package com.csg.leetcode.leet;

/**
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class Hot221MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int dp[][] = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = matrix[i][j] == '0' ? 0 : (i - 1 >= 0 ? dp[i - 1][j] + 1 : 1);
            }
        }

        int max = Integer.MIN_VALUE;
        int[] stack = new int[M + 1];
        for (int i = 0; i < N; i++) {
            max = Math.max(max, maxStack(dp[i],stack));
        }
        return max;
    }

    public static int maxStack(int[] arr,int [] stack) {

        int maxTail = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            while (maxTail != -1 && arr[stack[maxTail]] >= arr[i]) {
                int cur = stack[maxTail--];
                int left = maxTail >= 0 ? stack[maxTail] + 1 : 0;
                int right = i - 1;
                max = (int) Math.max(max, Math.pow(Math.min(arr[cur], right - left + 1), 2));
            }
            stack[++maxTail] = i;
        }
        while (maxTail != -1) {
            int cur = stack[maxTail--];
            int left = maxTail >= 0 ? stack[maxTail] + 1 : 0;
            max = (int) Math.max(max, Math.pow(Math.min(arr[cur], arr.length - left), 2));

        }
        return max;
    }

    public static void main(String[] args) {
//        char[][] matrix = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };
        char[][] matrix = {{'1'}};

//        char[][] matrix = {{'0','1'},{'0','1'}};
        int i = maximalSquare(matrix);
        System.out.println(i);
    }
}
