package com.csg.leetcode.leet.graph;

import java.util.*;

public class N1971 {

    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);


        }
        Set<Integer> done = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (node == destination) {
                return true;
            }

            ArrayList<Integer> nodes = graph[node];
            for (Integer other : nodes) {
                if (!done.contains(other)) {
                    queue.add(other);
                    done.add(node);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        boolean b = validPath(3, edges, 0, 2);
        System.out.printf("" + b);
    }
}
