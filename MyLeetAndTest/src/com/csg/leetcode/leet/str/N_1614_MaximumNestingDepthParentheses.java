package com.csg.leetcode.leet.str;

import java.util.Arrays;

/**
 * 有效括号的最大深度
 * 
 * @author zhangxu
 *
 */
public class N_1614_MaximumNestingDepthParentheses {
	 public static int maxDepth(String s) {
		 if(null == s || s.length() == 0){
			 return 0;
		 }
		 int max = 0;
		 //深度
		 int deep = 0;
		 for(int i = 0;i < s.length();i++){
			 
			 if(s.charAt(i) == '(')
				 deep++;
			 else if(s.charAt(i) == ')')
				 deep--;
			 
			if(deep > max){
				max = deep;
			}
		 }
		 return max;
	 }
	 
	 public static void main(String[] args) {
		System.out.println(maxDepth("((())()))"));
	}
}
