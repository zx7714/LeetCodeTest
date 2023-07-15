package com.csg.leetcode.leet;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/keys-and-rooms/
 */
public class No841KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> travel = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            travel.add(cur);
            for (Integer key : rooms.get(cur)) {
                if (key != cur && !travel.contains(key)) {
                    queue.add(key);
                }
            }
        }
        return rooms.size() == travel.size();
    }
}
