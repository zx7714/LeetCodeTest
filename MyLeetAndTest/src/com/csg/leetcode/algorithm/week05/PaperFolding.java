package com.csg.leetcode.algorithm.week05;

public class PaperFolding {
	public static void folding(int N){
		process(1, N, true);
	}
	/**
	 * 
	 * @param i 当前递归的层数
	 * @param N 递归的总层数
	 * @param down 凹凸？ down = true 凹；down = false 凸
	 */
	public static void process(int i,int N,boolean down) {
		if(i > N) {
			return;
		}
		process(i+1, N, true);
		System.out.println(down ? '凹' : '凸');
		process(i+1, N, false);
	}
	public static void main(String[] args) {
		int N = 3;
		folding(N);
	}
}
