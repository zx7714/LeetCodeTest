package com.csg.leetcode.leet.graph;

public class N1791 {
    public int findCenter(int[][] edges) {
        if (null == edges || edges.length == 0) {
            return 0;
        }

        int N = edges.length + 1;
        int in[] = new int[N + 1];

        for (int[] edge : edges) {
            in[edge[0]]++;
            in[edge[1]]++;
        }

        for (int i = 0; i <= N; i++) {
            if (in[i] == N -1) {
                return i;
            }
        }
        return 0;
    }
}
