package com.csg.leetcode.leet.str;
/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 
 * @author zhangxu
 *
 */
public class N_32_LongestValidParentheses {
	 public static int longestValidParentheses(String s) {
		 if(s == null || s.length() == 0){
			 return 0;
		 }
		 int[] arr = new int[s.length()];
		 arr[0] = 0;
		 int idx; //标记计算的那个位置
		 int max = 0;
		 for(int i = 1; i < s.length();i++){
			 idx = i - arr[i-1] -1;
			 if(s.charAt(i) == '(' || 
				 idx < 0 || 
				 s.charAt(idx) != '('){
				 
				 arr[i] = 0;
				 continue;
			 }
			 
			 arr[i] = arr[i-1] +2;
			 if(idx-1 > 0 && arr[idx-1] >0){
				 arr[i] += arr[idx -1];
			 }
			 
			 max = Math.max(max, arr[i]);
		 }
		 
		 return max;
	 }
	 
	public static void main(String[] args) {
		String s = "()()(())";
		System.out.println(longestValidParentheses(s));
	}
}
