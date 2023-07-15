package com.csg.leetcode.leet.union_set;

public class N765 {
    // 问题转换，有交换多少次，转换为了有多少情侣坐错了
    // 坐对的情侣，除以2后结果相同，并查集不会对其进行合并
    public int minSwapsCouples(int[] row) {
        if (null == row) {
            return 0;
        }
        int len = row.length;
        // 所有情侣数
        int n = len / 2;
        UnionSet us = new UnionSet(n);

        for (int i = 0; i < len; i += 2) {
            us.union(row[i] / 2, row[i + 1] / 2);
        }
        return n - us.count;
    }

    class UnionSet {
        int[] parent;
        // 表示坐错位置的情侣数
        int count;

        public UnionSet(int n) {
            parent = new int[n];
            count = n;
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
                count--;
            }
        }
    }

}
