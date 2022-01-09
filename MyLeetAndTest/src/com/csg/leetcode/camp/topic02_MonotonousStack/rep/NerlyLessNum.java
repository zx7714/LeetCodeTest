package com.csg.leetcode.camp.topic02_MonotonousStack.rep;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NerlyLessNum {
	public static int[][] getNearlyLessNoRepeat(int arr[]){
		if(arr== null || arr.length == 0){
			return null;
		}
		int[][] ans = new int[arr.length][2];
		Stack<Integer> s = new Stack<>();
		for(int i = 0;i<arr.length;i++){
			while(!s.isEmpty() && arr[s.peek()] > arr[i]){
				int popIndex = s.pop();
				int leftIndex = s.isEmpty() ? -1 : s.peek();
				ans[popIndex][0] = leftIndex;
				ans[popIndex][1] = i;
			}
			s.push(i);
		}
		while(!s.isEmpty()){
			int popIndex = s.pop();
			int leftIndex = s.isEmpty() ? -1 : s.peek();
			ans[popIndex][0] = -1;
			ans[popIndex][1] = leftIndex;
		}
		
		return ans;
	}
	
	public static int[][] getNearlyLess(int arr[]){
		if(arr==null || arr.length == 0){
			return null;
		}
		int[][] ans = new int[arr.length][2];
		Stack<List<Integer>> s = new Stack<>();
		for(int i = 0 ;i<arr.length;i++){
			while(!s.isEmpty() && arr[s.peek().get(0)] > arr[i]){
				List<Integer> pops = s.pop();
				int leftIndex = s.isEmpty() ? -1 : s.peek().get(s.peek().size()-1);
				for(int popIndex : pops){
					ans[popIndex][0] = leftIndex;
					ans[popIndex][1] = i;
				}
			}
			if(!s.isEmpty() && arr[s.peek().get(0)] == arr[i]){
				s.peek().add(i);
			}else{
				List<Integer> item = new ArrayList<>();
				item.add(i);
				s.push(item);
			}
		}
		while(!s.isEmpty()){
			List<Integer> pops = s.pop();
			int leftIndex = s.isEmpty() ? -1 : s.peek().get(s.peek().size() -1 );
			for(int popIndex : pops){
				ans[popIndex][0] = leftIndex;
				ans[popIndex][1] = -1;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		int[] arr = {1,2,2,6,3,3,4,5};
		getNearlyLess(arr);
	}
}
