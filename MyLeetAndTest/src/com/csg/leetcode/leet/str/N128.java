package com.csg.leetcode.leet.str;

import java.util.*;

public class N128 {
    public static int longestConsecutive(int[] nums) {

        UnionSet us = new UnionSet(nums);
        Set<Integer> done = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!done.contains(done)) {
                us.union(nums[i], nums[i] + 1);
                done.add(nums[i]);
            }
        }
        Map<Integer, Integer> sizes = us.sizes;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> e : sizes.entrySet()) {
            max = Math.max(e.getValue(), max);
        }
        return max;
    }


    static class UnionSet {
        Map<Integer, Integer> parents;
        Map<Integer, Integer> sizes;

        public UnionSet(int[] nums) {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                parents.put(nums[i], nums[i]);
                sizes.put(nums[i], 1);
            }
        }

        public Integer findParent(int i) {
            if (!parents.containsKey(i)) {
                return null;
            }
            Integer parent = i;
            Queue<Integer> q = new LinkedList<>();
            while (null != parent && parent != parents.get(parent)) {
                q.add(parent);
                parent = parents.get(parent);
            }
            while (!q.isEmpty() && null != parent) {
                parents.put(q.poll(), parent);
            }
            return parent;
        }

        public void union(int i, int j) {
            Integer iParent = findParent(i);
            Integer jParent = findParent(j);
            if (iParent == null || jParent == null) {
                return;
            }
            if (iParent != jParent) {
                int iSize = sizes.get(iParent);
                int jSize = sizes.get(jParent);
                Integer maxParent = iSize > jSize ? iParent : jParent;
                Integer minParent = maxParent == iParent ? jParent : iParent;
                parents.put(minParent, maxParent);
                sizes.put(maxParent,iSize + jSize);
                sizes.remove(minParent);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, -3, -3, 7, -3, 0, 5, 0, -8, -4, -1, 2};

        int i = longestConsecutive(nums);
        System.out.println(i);
    }
}
