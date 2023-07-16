package com.csg.leetcode.leet.graph;

public class N997 {
    public static int findJudge(int n, int[][] trust) {

        if (n == 1) {
            return -1;
        }

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        for (int[] rel : trust) {
            in[rel[1]]++;
            out[rel[0]]++;
        }

        for (int i = 0; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] trust = {{1, 2}};
        findJudge(2, trust);
    }
}
