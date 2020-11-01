package com.csg.leetcode.leet;
/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。

	如果不存在公共前缀，返回空字符串 ""。
	
	示例 1:
	
	输入: ["flower","flow","flight"]
	输出: "fl"
	示例 2:
	
	输入: ["dog","racecar","car"]
	输出: ""
	解释: 输入不存在公共前缀。
	说明:
	
	所有输入只包含小写字母 a-z 。
 * @author ZhangXuAo
 *
 */
public class LongestCommotPrefix {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strs = {"a","b"};
		String result = new LongestCommotPrefix().longestCommontPrefixSecondSearch(strs);
		System.out.println(result);
	}
	public String longestCommontPrefixMine(String strs[]){
		if(strs.length==0){
			return "";
		}
		if(strs.length==1){
			return strs[0];
		}
		String str = strs[0];
		String result = "";
		for(int i = 0;i<str.length();i++){
			int count = 0;
			for(int j = 1;j<strs.length;j++){
				if(strs[j].length() >= str.substring(0, i+1).length() &&
						strs[j].startsWith(str.substring(0, i+1))){
					  count++;
				}
			}
			if(count == strs.length-1){
				result=str.substring(0,i+1);
			}
		}
		return result;
	}
	/**
	 * 二分查找法
	 * @param strs
	 * @return
	 */
	public String longestCommontPrefixSecondSearch(String strs[]){
		if(strs == null || strs.length ==0)
			return "";
		int minLen = Integer.MAX_VALUE;
		for(String str : strs)
			minLen = Math.min(minLen, str.length());
		int low = 1; //开头
		int high = minLen;//结尾
		while(low <= high){
			int mid = (low + high)/2;//中间值
			if(ifContent(strs,mid)){
				low = mid +1; //如果所有字符数组都包含，则开始值设置为中间值+1
			}else{
				high = mid-1;//如果不是所有数组都包含，怎结束值设为中间值-1
			}
		}
		return strs[0].substring(0,(low+high)/2);
	}
	private boolean ifContent(String strs[],int len){
		String str = strs[0];
		for(int i = 1;i<strs.length;i++)
			if(!strs[i].startsWith(str.substring(0,len)))
				return false;
		return true;
	}
}
