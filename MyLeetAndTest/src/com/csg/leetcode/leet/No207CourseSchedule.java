package com.csg.leetcode.leet;

import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/course-schedule/
 */
public class No207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0 && numCourses != 0) {
            return false;
        }
        boolean[] visited = new boolean[numCourses + 1];

        visited[prerequisites[0][0]] = true;
        for (int i = 0; i < prerequisites.length;i++) {
            if (visited[prerequisites[0][1]]) {
                return false;
            }
            visited[prerequisites[0][1]] = true;
         }
        return true;
    }
}
