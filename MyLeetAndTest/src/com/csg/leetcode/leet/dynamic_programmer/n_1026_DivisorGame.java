package com.csg.leetcode.leet.dynamic_programmer;

public class n_1026_DivisorGame {
	public static boolean divisor(int n) {
		return f(n);
	}
	
	
	public static boolean f(int n) {
		if(n == 1) {
			return false;
		}
		for(int i = 1;i<n && n % i==0;i++) {
			if(!f(n-i)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean dp(int N) {
		if(N==1) {
			return false;
		}
		boolean[] dp = new boolean[N+1];
		dp[1] = false;
		dp[2] = true;
		for(int i = 3 ;i <= N;++i) {
			for(int j = 1;j<i;++j) {
				if(i%j==0 && !dp[i-j]) {
					dp[i] = true;
					break;
				}
			}
			
		}
		return dp[N];
	}
	
	
	
	public static void main(String[] args) {
		int n = 4;
		System.out.println(dp(n));
	}
}
