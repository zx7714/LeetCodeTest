package com.csg.leetcode.leet.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class N310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }

        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] outArr = new int[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            outArr[edge[0]]++;
            outArr[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outArr[i] == 1) {
                queue.add(i);
            }
        }
        int[] visited = new int[n];
        while (!queue.isEmpty()) {
            ans = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                visited[poll] = 1;
                ans.add(poll);
                for (int j : graph[poll]) {
                    if (visited[j] != 1 && --outArr[j] == 1) {
                        queue.add(j);
                    }
                }

            }
        }
        return ans;
    }
}
