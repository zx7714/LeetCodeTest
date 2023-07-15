package com.csg.leetcode.leet.union_set;

import java.util.Arrays;

public class N685 {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        if (null == edges || edges.length == 0) {
            return new int[2];
        }
        int len = edges.length;
        int[] in = new int[len + 1];
        int[] doubleEdg = null;
        for (int[] edge : edges) {
            in[edge[1]]++;
            if (in[edge[1]] == 2) {
                doubleEdg = edge;
            }
        }
        UnionSet us = new UnionSet(len + 1);
        // 无入度为2的边
        if (doubleEdg == null) {
            for (int i = 0; i < len; i++) {
                if (us.isSame(edges[i][0], edges[i][1])) {
                    return edges[i];
                } else {
                    us.union(edges[i][0], edges[i][1]);
                }
            }
        } else {
            boolean circle = false;
            for (int i = 0; i < len; i++) {
                if (Arrays.equals(edges[i], doubleEdg)) {
                    continue;
                }
                if (us.isSame(edges[i][0], edges[i][1])) {
                    circle = true;
                } else {
                    us.union(edges[i][0], edges[i][1]);
                }
            }
            if (!circle) {
                return doubleEdg;
            }
            for (int[] edge : edges) {
                if(edge[1] == doubleEdg[1]){
                    return edge;
                }
            }
        }
        return null;
    }

    class UnionSet {
        private int[] parent;

        public UnionSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent != yParent) {
                parent[xParent] = yParent;
            }
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

    }
}
