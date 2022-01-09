package com.csg.leetcode.leet.nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.csg.leetcode.algorithm.week03.TrieTree.Right;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

	当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
	
	请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
	
	注意：不允许旋转信封。
 * @author zhangxu
 *
 */
public class N_345_RussianDollEnvelopes {
	public static int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, (o1,o2)->{
			if(o1[0] != o2[0]){
				return o1[0] - o2[0];
			}else{
				return o2[1] - o1[1];
			}
		});
		int[] dp = getDp(envelopes);
		int ans = generateList(dp,envelopes);
		return ans;
    }
	private static int generateList(int[] dp,int[][] envelopes) {
		int maxLen = 0;
		for(int i = 0; i < dp.length;i++){
			maxLen = Math.max(dp[i],maxLen);
			// index = i;
		}
//		int help[] = new int[maxLen];
//		help[--maxLen] = envelopes[index][0];
//		for(int i = index;i >=0;i--){
//			if(envelopes[i][1] < envelopes[index][1] && 
//					dp[i] == dp[index] -1 &&
//					envelopes[i][0] != envelopes[index][0]){
//				help[--maxLen] = envelopes[i][0];
//				index = i;
//			}
//		}
//		int max = 1;
//		for(int i = 1;i < help.length;i++){
//			if(help[i] > help[i-1]){
//				max ++;
//			}
//		}
		return maxLen;
	}
	private static int[] getDp(int[][] envelopes) {
		int[] ends = new int[envelopes.length];
		int[] dp = new int[envelopes.length];
		dp[0] = 1;
		ends[0] = envelopes[0][1];
		int side = 0; 
		int l = 0;
		int r = 0;
		int m = 0;
		for(int i = 0 ; i < envelopes.length;i++){
			l = 0;
			r = side;
			while(l <= r){
				m = r -((r - l) >>2);
				if(envelopes[i][1] > ends[m] ){
					l = m + 1;
				}else{
					r = m -1;
				}
			}
			side = Math.max(l, side);
			ends[l] = envelopes[i][1];
			dp[i] = l + 1;
		}
		return dp;
	}
	public static void main(String[] args) {
		int[][] arr = {{4,5},{4,6},{6,7},{2,3},{1,1},{1,1}};
		System.out.println(maxEnvelopes(arr));
	}
}
