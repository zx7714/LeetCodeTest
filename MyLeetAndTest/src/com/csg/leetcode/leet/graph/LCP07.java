package com.csg.leetcode.leet.graph;

import java.rmi.UnexpectedException;
import java.util.ArrayList;


public class LCP07 {

    public static int numWays(int n, int[][] relation, int k) {

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] rel : relation) {
            graph[rel[0]].add(rel[1]);
        }
        return process(0, 0, graph, k, n);
    }

    public static int process(int index, Integer node, ArrayList<Integer>[] graph, int k, int n) {
        if (graph[node].size() == 0) {
            return 0;
        }
        if (index == k -1) {
            return graph[node].contains(n - 1) ? 1 : 0;
        } else {
            int res = 0;
            for (Integer nextNode : graph[node]) {
                res += process(index + 1, nextNode, graph, k, n);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[][] relation = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        System.out.printf("" + numWays(5, relation, 3));
    }
}
