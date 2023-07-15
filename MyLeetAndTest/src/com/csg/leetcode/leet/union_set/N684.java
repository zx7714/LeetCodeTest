package com.csg.leetcode.leet.union_set;

import java.util.List;

public class N684 {
    public int[] findRedundantConnection(int[][] edges) {

        if (null == edges || edges.length == 0) {
            return new int[0];
        }
        int[] ans = new int[2];
        UnionSet us = new UnionSet(edges.length + 1);
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            if (us.sameSet(x, y)) {
                ans = edges[i];
            } else {
                us.union(x, y);
            }
        }
        return ans;
    }

    class UnionSet {
        int parent[];

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

        public boolean sameSet(int x, int y) {
            return find(x) == find(y);
        }
    }
}
