package com.csg.leetcode.camp2;

public class N4_PackagingRobbet_Leet_517 {
	/*
	 * 以i为轴，找左边部分的多出或差多少，右边部分多出或差多少 --- 差值就是需要移动的数量。
	 * 然后求需要调整的数的最大值。
	 * 	1.两边都大于0或有一边大于0，找最大需要移动数
	 *  2.两边都小于0，需要从i移动到两边，一次只能移动一个，所有需要移动两个边绝对值之和次、
	 *  3.找到最大的需要移动数，即为结果
	 */
	public static int packaging(int arr[]){
		int nums = 0;
		int size = arr.length;
		for(int i = 0;i < size;i++){
			nums += arr[i];
		}
		if(nums % size != 0){
			return -1;
		}
		int avg = nums / size;
		int max = Integer.MIN_VALUE;
		int leftSum = 0;
		int rightSum = 0;
		int rightNeed = 0;
		int leftNeed = 0;
		
		for(int i = 0;i<size;i++){
			
			leftNeed = leftSum - i * avg;
			rightSum = nums- leftSum - arr[i];
			rightNeed = rightSum -(size - i -1)*avg;
			
			if(leftNeed < 0 && rightNeed <0){
				max = Math.max(Math.abs(leftNeed) + Math.abs(rightNeed),max);
			}else{
				max = Math.max(Math.max(Math.abs(leftNeed), Math.abs(rightNeed)),max);
			}
			leftSum += arr[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] arr = {0,2,1,2,0};
		System.out.println(packaging(arr));	
	}
}
