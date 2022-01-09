package com.csg.leetcode.leet;

/**
 * 假设你现在正在爬楼梯，楼梯有 n 级。每次你只能爬 1级或者 2级，那么你有多少种方法爬到楼梯的顶部？
 * @author zhangxu
 *
 */
public class ClimbTheStairs {
	public static int climb(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return process(0,n);
	}

	private static int process(int s,int n) {
		if(s == n-1) {
			return 1;
		}
		if(s == n-2) {
			return 2;
		}
		return process(s+1,n)+process(s+2, n);
	}
	
	
	public static int climb2(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		int arr[] = new int[n+1];
		return process2(0,n,arr);
	}

	private static int process2(int s,int n,int arr[]) {
		if(s+1 == n) {
			arr[s] = 1;
			return 1;
		}
		if(s+2 == n) {
			arr[s] = 2;
			return 2;
		}
		int p1 = -1;
		if(arr[s+1] != 0) {
			p1 = arr[s+1];
		}else {
			p1 = process2(s+1, n,arr);
			arr[s+1] = p1;
			
		}
		int p2 = -1;
		if(arr[s+2] != 0) {
			p2 = arr[s+2];
		}else {
			p2 = process2(s+2, n,arr);
			arr[s+2] = p2;
		}
		return p1 + p2;
	}
	
	public static void main(String[] args) {
		int n = 8;
		int climb = dp(n);
		System.out.println(climb);
	}
	
	public static int dp(int n ) {
		if(n == 0) {
			return 0;
		}
		int[] dp = new int[n+1];
		
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3;i<dp.length;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		
		return dp[n];
	}
}
