package com.csg.leetcode.camp.topic07Manacher.rep;

public class Manacher_Rep_2 {
	public static int manacher(String str){
		
		char[] chars = manacherString(str.toCharArray());
		int c = -1;
		int r = -1;
		int[] pArr = new int[chars.length];
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < chars.length;i++){
			pArr[i] = r > i ? Math.min(r - i, pArr[2*c - i]) :  1;
			while(i+pArr[i] < chars.length && i - pArr[i] > -1){
				if(chars[i+pArr[i]] == chars[i - pArr[i]]){
					pArr[i] ++;
				}else{
					break;
				}
			}
			
			if(i + pArr[i] > r){
				r = i + pArr[i];
				c = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max;
	}

	private static char[] manacherString(char[] chars) {
		char[] newChars = new char[chars.length + 1];
		int idx = 0;
		for(int i = 0 ;i<newChars.length;i++){
			newChars[i] = (i & 1) == 0 ? '#' : chars[idx++];
		}
		return newChars;
	}
}