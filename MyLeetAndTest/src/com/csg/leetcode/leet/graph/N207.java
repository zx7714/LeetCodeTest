package com.csg.leetcode.leet.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N207 {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] inArr = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
            inArr[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < numCourses; i++) {
            if (inArr[i] == 0) {
                queue.add(i);
            }
        }

        int learn = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            learn++;
            for (Integer p : graph[poll]) {
                if (--inArr[p] == 0) {
                    queue.add(p);
                }
            }
        }
        return learn == numCourses;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}};
        canFinish(2, edges);
    }
}
