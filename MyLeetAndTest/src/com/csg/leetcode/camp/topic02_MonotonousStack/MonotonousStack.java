package com.csg.leetcode.camp.topic02_MonotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组，求数组的当前元素i，左边离他最近比他小的，右边离他最近比他小的。
 * @author zhangxu
 *
 */
public class MonotonousStack {
	public static int[][] getNearLessNoRepeat(int[] arr){
		if(null == arr || arr.length == 0){
			return null;
		}
		int[][] ans = new int[arr.length][2];
		//单调栈，值由小到大
		Stack<Integer> s = new Stack<>();
		
		for(int i =0;i < arr.length;i++){
			//栈顶的值比当前值大，弹出，谁让弹出他右边的最小值就是谁，左边最小值是栈顶的值。
			while(!s.isEmpty() && arr[s.peek()] > arr[i]){
				int popIndex = s.pop();
				int leftIndex = s.isEmpty() ? -1 : s.peek();
				ans[popIndex][0] = leftIndex;
				ans[popIndex][1] = i; 
			}
			//如果当前值比栈顶值大直接入栈
			s.push(i);
		}
		//数组遍历完后，处理栈中的数，此时右侧已经没有比当前值小的值了。
		while(!s.isEmpty()){
			int popIndex = s.pop();
			int leftIndex = s.isEmpty() ? -1 : s.peek();
			ans[popIndex][0] = leftIndex;
			ans[popIndex][1] = -1;
		}
		return ans;
	}
	
	public static int[][] getNearlyLessWithRepeat(int[] arr){
		if(null == arr || arr.length == 0){
			return null;
		}
		int[][] ans = new int[arr.length][2];
		Stack<List<Integer>> s = new Stack<>();
		for(int i = 0 ; i < arr.length ;i++){
			while(!s.isEmpty() && arr[s.peek().get(0)] > arr[i]){
				
				List<Integer> pids = s.pop();
				int leftIndex = s.isEmpty() ? -1 : s.peek().get(s.peek().size()-1);
				
				for(int popIndex : pids){
					ans[popIndex][0] = leftIndex;
					ans[popIndex][1] = i;
				}
			}
			if(!s.isEmpty() && arr[s.peek().get(0)] == arr[i]){
				s.peek().add(i);
			}else{
				List<Integer> list = new ArrayList<>();
				list.add(i);
				s.push(list);
			}
			
		}
		
		while(!s.isEmpty()){
			List<Integer> popIndexs = s.pop();
			int leftIndex = s.isEmpty() ? -1 : s.peek().get(s.peek().size()-1);
			for(int popIndex : popIndexs){
				ans[popIndex][0] = leftIndex;
				ans[popIndex][1] = -1;
			}
		}
		
		return ans;
		
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,2,6,3,3,4,5};
		getNearlyLessWithRepeat(arr);
	}
}
