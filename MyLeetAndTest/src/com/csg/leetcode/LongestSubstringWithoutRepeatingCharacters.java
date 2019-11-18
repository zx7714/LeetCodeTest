package com.csg.leetcode;

import java.util.HashMap;
/** 无重复最长字符串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

	示例 1:
	
	输入: "abcabcbb"
	输出: 3 
	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
	示例 2:
	
	输入: "bbbbb"
	输出: 1
	解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
	示例 3:
	
	输入: "pwwkew"
	输出: 3
	解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
	     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @author ZhangXuAo
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = slidingWindow("abba");
		System.out.println(i);
	}
	 public static int lengthOfLongestSubstring(String s) {
		 char[] str = s.toCharArray();
		 int max = 0;
		 int count = 0;//出现重复后以最近一次重复的位置为起点。
		 for(int i=0;i<str.length;i++){//循环char数组
			 for(int j = count;j<i;j++){//循环i前面的
				 if(str[i] == str[j]){
					 count = j+1;//出现重复重新设置起点
					 break;
				 }
			 }
			 if(i-count+1>max){//当前指标-起点=不重复的位置数
				 max = i-count+1;
			 }
		 }
		 return max;
	 }
	 /**
	  * 滑动窗口
	  * @param s
	  * @return
	  */
	 private static int slidingWindow(String s){
		 int ans = 0;
		 HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		 for(int i=0,j=0 ;i<s.length();i++){
			 if(map.containsKey(s.charAt(i))){
				 j = Math.max(map.get(s.charAt(i)),j);
			 }
			 ans = Math.max(ans, i-j+1);
			 map.put(s.charAt(i),i+1);
		 }
		 return ans;
	 }

}
