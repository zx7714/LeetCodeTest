package com.csg.leetcode.leet;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class Hot11ContainerWithMostWater {
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (L < R) {
            if (height[L] == height[R]) {
                max = Math.max(max,(L - R) * height[R]);
                L++;
            }else if (height[L] > height[R]) {
                max = Math.max(max,(L - R) * height[R]);
                R--;
            } else {
                max = Math.max(max,(L - R) * height[L]);
                L++;
            }
        }
        return max;
    }
}
