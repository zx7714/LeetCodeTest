package com.csg.leetcode.algorithm.week06;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 输入：整数数组costs、整数数组profiits、整数K、整数M 
 * 
 * costs[i] 表示i号项目的花费
 * 
 * profits[i] 表示i号项目在扣除花费之后还能挣到的钱
 * 
 * K表示你只能串行的最多做k个项目
 * 
 * M表示你初始的资金
 * 
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目 
 * 输出：你最后获得的最大钱数
 * 
 * @author zhangxu
 *
 */
public class IPO {
	
	public static class Program{
		int c;
		int p;
		
		public Program(int c,int p){
			this.c = c;
			this.p = p;
		}
	}
	
	public static int ipo(int[] costs, int[] profits, int K, int W){
		if(costs == null || profits == null){
			return W;
		}
		Queue<Program> minCostsQueue = new PriorityQueue<>(new Comparator<Program>() {
			@Override
			public int compare(Program o1, Program o2) {
				return o1.c - o2.c;
			}
		});
		Queue<Program> maxProfitsQueue = new PriorityQueue<>(new Comparator<Program>() {
			@Override
			public int compare(Program o1, Program o2) {
				return o2.p - o1.p;
			}
		});
		
		for(int i = 0;i<profits.length;i++){
			minCostsQueue.add(new Program(costs[i], profits[i]));
		}
		
		for(int i = 0;i<K;i++){
			while(!minCostsQueue.isEmpty() && minCostsQueue.peek().c <= W){
				maxProfitsQueue.add(minCostsQueue.poll());
			}
			if(maxProfitsQueue.isEmpty()){
				return W;
			}
			W += maxProfitsQueue.poll().p;
		}
		
		return W;
	}
	
	public static void main(String[] args) {
		int[] proits = {1,2,3};
		int[] costs = {0,1,1};
		int K = 2;
		int W = 0;
		System.out.println(ipo(costs,proits,K,W));
	}

}
