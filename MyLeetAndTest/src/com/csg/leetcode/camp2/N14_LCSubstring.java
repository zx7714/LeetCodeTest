package com.csg.leetcode.camp2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * 给定两个str1和str2，求最长公共子串
 * @author zhangxu
 *
 */
public class N14_LCSubstring {
	public static String lcstr(String s1,String s2){
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		
		int row = 0; //行
		int col = s2.length() -1; //列
		int max = 0;
		int end = 0;
		
		while(row < str1.length){
			int i = row;
			int j = col;
			int len = 0; // 公共子串的长度
			while(i < str1.length && j < str2.length){
				// i、j是否相等，如果相等增加长度，继续判断
				if(str1[i] != str2[j]){
					len = 0;
				}else{
					len++;
				}
				// 更新max值
				if(len > max){
					end = i;
					max = len;
				}
				i++;
				j++;
			}
			//先处理列，再处理行
			if(col > 0){
				col--;
			}else{
				row++;
			}
		}
		
		CompletableFuture<String> cp = CompletableFuture.supplyAsync(() ->{
			return "123";
		});
		try {
			String string = cp.get();
			System.out.println(string);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return s1.substring(end - max + 1, end + 1);
	}
	public static void main(String[] args) {
		String str1 = "ABC1234567DEFG";
		String str2 = "HIJKL1234567MNOP";
		System.out.println(lcstr(str1, str2));
	}
}
