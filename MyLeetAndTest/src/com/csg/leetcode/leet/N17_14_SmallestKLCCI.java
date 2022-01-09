package com.csg.leetcode.leet;

import java.util.Arrays;

public class N17_14_SmallestKLCCI {
	public static int[] smallestK(int[] arr, int k) {
		int[] arr2 = copy(arr);

		process(arr2, 0, arr2.length - 1, k);
		int[] ans = new int[k];
		for (int i = 0; i < k; ++i) {
			ans[i] = arr2[i];
		}
		return ans;
	}

	public static void process(int[] arr, int L, int R, int k) {
		if (L == R) {
			return;
		}
		int target = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partation(arr, L, R, target);
		if (k >= range[0] && k <= range[1]) {
			return;
		} else if (k < range[0]) {
			process(arr, L, range[0] - 1, k);
		} else {
			process(arr, range[1] + 1, R, k);
		}
	}

	public static int[] partation(int[] arr, int L, int R, int target) {
		int bigger = R + 1;
		int smaller = L - 1;
		int index = L;
		while (index < bigger) {
			if (arr[index] > target) {
				swap(arr, index, --bigger);
			} else if (arr[index] < target) {
				swap(arr, index++, ++smaller);
			} else {
				index++;
			}
		}
		return new int[] { bigger - 1, smaller + 1 };
	}

	private static void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static int[] copy(int[] arr) {
		int[] cArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			cArr[i] = arr[i];
		}
		return cArr;
	}
	public static void main(String[] args) {
		int arr[] = {1,2,3,2,2,3,4,5};
		System.out.println(Arrays.toString(smallestK(arr,3)));
	}
}
