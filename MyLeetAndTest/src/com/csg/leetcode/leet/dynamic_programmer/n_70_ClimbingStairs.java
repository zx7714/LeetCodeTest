package com.csg.leetcode.leet.dynamic_programmer;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * @author zhangxu
 *
 */
public class n_70_ClimbingStairs {
	
	public static int climbStairs(int n) {
		if(n == 1 || n == 2 || n == 0) {
			return n;
		}
		
		int[] dp = new int[n+1];
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3;i<dp.length;i++) {
			dp[i] = dp[i-2] + dp[i-1];
		}
		return dp[n];
    }
	
	public static void main(String[] args) {
		int n = 4;
		System.out.println(climbStairs(n));
	}
}
