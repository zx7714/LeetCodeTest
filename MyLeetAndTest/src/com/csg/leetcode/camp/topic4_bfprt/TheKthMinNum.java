package com.csg.leetcode.camp.topic4_bfprt;
/**
 * 给定一个数组，找出第k小的数
 * @author zhangxu
 *
 */
public class TheKthMinNum {
	public static int kThMin(int arr[],int k){
		if(null == arr || arr.length == 0 || k > arr.length){
			return -1;
		}
		
		return process(arr, 0, arr.length-1, k-1);
		
	}
	public static int process(int[] arr,int L,int R,int k){
		if(L == R){
			return arr[L];
		}
		
		int target = L + (int)(Math.random()*(R-L + 1));
		//荷兰国旗问题
		int[] res = partition(arr, L, R, arr[target]);
		//如果k在等于区，则返回
		if(k >=res[0] && k <= res[1]){
			return arr[k];
		}else if(k < res[0]){ //在小于区
			return process(arr, L, res[0]-1, k);
		}else{ //在大于区
			return process(arr, res[1]+1, R, k);
		}
	}
	
	private static int[] partition(int[] arr,int L,int R,int k) {
		int smaller = L -1;
		int bigger = R + 1;
		int index = L;
		
		while(index < bigger){
			if(arr[index] > k){
				swap(arr, index, --bigger);
			}else if(arr[index] < k){
				swap(arr, index++, ++smaller);
			}else{
				index++;
			}
		}
		return new int[]{smaller+1,bigger-1};
	}
	private static void swap(int[] arr,int i,int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) {
		int arr[] = {1,3,4,3,5,6,7,2,-1};
		System.out.println(kThMin(arr,3));
	}
}
