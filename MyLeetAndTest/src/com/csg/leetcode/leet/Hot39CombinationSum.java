package com.csg.leetcode.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class Hot39CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(candidates[i]);
            process(candidates, i, target, candidates[i], next, ans);
        }
        return ans;
    }

    public static void process(int[] arr, int index, int target, int beforeRes, List<Integer> res, List<List<Integer>> ans) {
        if (beforeRes > target) {
            return;
        }
        if (beforeRes == target) {
            ans.add(res);
            return;
        }
        for (int i = index; i < arr.length; i++) {
            if (beforeRes + arr[i] > target) {
                continue;
            }
            List<Integer> next = new ArrayList<>(res);
            next.add(arr[i]);
            process(arr, i, target, beforeRes + arr[i], next, ans);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        List<List<Integer>> lists = combinationSum(arr, 7);
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }

    }
}
