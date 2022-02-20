package com.csg.practice.D6M2;

import java.util.Stack;

/**
 * 给定一个数组，求数组的子数组的sum和子数组的min的最大值
 */
public class No2MaxSonArraySumAndMin {
    public static int arraySumAndMin(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] sumArr = new int[N];
        sumArr[0] = arr[0];
        for (int i = 1; i < N;i++) {
            sumArr[i] += sumArr[i-1] + arr[i];
        }
        Stack<Integer> s = new Stack<>();
        int ans = 0;
        for (int i = 0; i < N;i++) {
            while(!s.empty() && arr[s.peek()] >= arr[i]){
                int popIndex = s.pop();

                ans = Math.max(ans,arr[popIndex] * (sumArr[i -1] - (s.empty() ? 0 : sumArr[s.peek()])));
            }
            s.push(i);
        }
        while (!s.empty()) {
            int popIndex = s.pop();
            int leftSum = s.empty() ? 0 : sumArr[s.peek()];
            ans = Math.max(ans,arr[popIndex] * (sumArr[N - 1] - leftSum));
        }
        return ans;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        System.out.println(arraySumAndMin(arr));
    }
}
