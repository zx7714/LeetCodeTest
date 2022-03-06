package com.csg.leetcode.leet;

import java.util.ArrayList;
import java.util.List;

public class Hot46Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length + 1];
        process(nums, 0, new ArrayList<>(), visited, ans);
        return ans;
    }

    static void process(int[] nums, int index, List<Integer> list, boolean[] visits, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visits[i]) {
                continue;
            }
            visits[i] = true;
            int iList = list.size();
            list.add(nums[i]);
            process(nums, index + 1, list, visits, ans);
            list.remove(iList);
            visits[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> permute = permute(arr);
        System.out.println(permute);
    }
}
