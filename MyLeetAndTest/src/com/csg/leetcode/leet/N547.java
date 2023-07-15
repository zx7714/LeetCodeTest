package com.csg.leetcode.leet;

public class N547 {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionSet us = new UnionSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    us.union(i, j);
                }
            }
        }
        return us.size;
    }

    class UnionSet {

        int[] parents;
        int size;

        public UnionSet(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            size = n;
        }

        public int find(int i) {

            while (i != parents[i]) {
                i = parents[i];
            }
            return i;
        }

        public void union(int i, int j) {
            int iParent = find(i);
            int jParent = find(j);
            if (iParent != jParent) {
                parents[jParent] = iParent;
                size--;
            }
        }

    }
}
