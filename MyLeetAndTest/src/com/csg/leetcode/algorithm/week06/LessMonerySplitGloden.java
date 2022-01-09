package com.csg.leetcode.algorithm.week06;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 3.一块金条切成两半，是需要花费和长度数值一样的铜板的。 
 * 比如长度为20的金条，不管怎么切，都需要花费20个铜板，一群人想要分整块金条，怎么分最省铜板
 * 
 * @author zhangxu
 *
 */
public class LessMonerySplitGloden {
	public static int split1(int[] glodens) {
		if(glodens == null || glodens.length == 0) {
			return 0;
		}
		
		return process(glodens,0);
	}
	/**
	 * 
	 * @param glodens 金条数
	 * @param pre 上次切分所花费
	 * @return
	 */
	private static int process(int[] glodens, int pre) {
		if(glodens.length == 1) {
			return pre;
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0 ;i < glodens.length ; i++) {
			for(int j = i+1;j < glodens.length;j++) {
				min = Math.min(min,process(copyAndMergeArray(glodens,i,j), pre));
			}
		}
		return min;
	}
	
	//复制并把已经切的金条合并放入数组中。
	private static int[] copyAndMergeArray(int[] glodens, int i, int j) {
		int[] ans = new int[glodens.length-1];
		int ansi = 0;
		for(int arri = 0;arri<glodens.length;arri++){
			if(arri != i && arri != j){
				ans[ansi++] = glodens[arri];
			}
		}
		//合并两个
		ans[ansi] = glodens[i] + glodens[j];
		return ans;
	}


	public static int split2(int[] glodens){
		if(glodens == null){
			return 0;
		}
		Arrays.sort(glodens);
		Queue<Integer> q = new PriorityQueue<>();
		for(int i = 0; i< glodens.length;i++){
			q.add(glodens[i]);
		}
		int cur = 0;
		int sum = 0;
		while(q.size() > 0){
			cur = q.poll()+q.poll();
			sum += cur;
			q.add(cur);
		}
		return sum;
	}
	
	// for test
		public static int[] generateRandomArray(int maxSize, int maxValue) {
			int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) (Math.random() * (maxValue + 1));
			}
			return arr;
		}

		public static void main(String[] args) {
			int testTime = 100000;
			int maxSize = 6;
			int maxValue = 1000;
			for (int i = 0; i < testTime; i++) {
				int[] arr = generateRandomArray(maxSize, maxValue);
				if (split1(arr) != split1(arr)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}
}
