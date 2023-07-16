package com.csg.leetcode.leet.graph;

import javax.naming.BinaryRefAddr;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] in = new int[numCourses];
        for (int[] arr : prerequisites) {
            graph[arr[1]].add(arr[0]);
            in[arr[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }
        int learn = 0;
        int[] sort = new int[numCourses];
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            sort[learn++] = poll;
            for (int i : graph[poll]) {
                if (--in[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return learn == numCourses ? sort : new int[0];
    }

}
