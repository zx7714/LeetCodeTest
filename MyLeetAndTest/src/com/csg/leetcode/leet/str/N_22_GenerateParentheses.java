package com.csg.leetcode.leet.str;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * @author zhangxu
 *
 */
public class N_22_GenerateParentheses {
	public static List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		if(n == 0){
			return ans;
		}
		
		generate(n - 1,n,"(",ans);
		return ans;
	}

	private static void generate(int ln,int rn,String type,List<String> ans) {
		if(rn == 0 && ln == 0){
			ans.add(type);
			return;
		}
		if(ln > 0){
			generate(ln-1, rn,type+"(",ans);
		}
		if(rn > 0 && rn > ln){
			generate(ln,rn - 1,type+")",ans);
		}
	}
	
	public static List<String> generateParenthesis2(int n){
		List<String> ans = new ArrayList<>();
		if(n == 0){
			return ans;
		}
		
		return ans;
	}
	
	
	
	public static void main(String[] args) {
		List<String> list = generateParenthesis(3);
		System.out.println(list);
	}
}
