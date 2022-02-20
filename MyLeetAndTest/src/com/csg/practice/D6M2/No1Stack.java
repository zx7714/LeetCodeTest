package com.csg.practice.D6M2;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个数组，求数组中每个元素离它近的比它小的数
 */
public class No1Stack {
    public static int[][] stack(int [] arr) {
        if (null == arr || arr.length == 0) {
            return new int[][] {};
        }
        Stack<Integer> stack = new Stack<>();
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length;i++) {
            while (!stack.empty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftIndex = stack.empty() ? -1 : stack.peek();
                ans[popIndex][0] = i;
                ans[popIndex][1] = leftIndex;
            }
            stack.push(i);
        }
        while (!stack.empty()) {
            Integer popIndex = stack.pop();
            Integer leftIndex = stack.empty() ? -1 : stack.peek();
            ans[popIndex][0] = -1;
            ans[popIndex][1] = leftIndex;
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] arr = {3,2,1,4,5,3};
        int[][] stack = stack(arr);
        for (int[] s : stack)
            System.out.println(Arrays.toString(s));
    }

}


