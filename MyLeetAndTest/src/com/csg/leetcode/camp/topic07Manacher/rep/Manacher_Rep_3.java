package com.csg.leetcode.camp.topic07Manacher.rep;

public class Manacher_Rep_3 {
	public static int manacher(String str){
		char[] chars = manacherStr(str.toCharArray());
		//回文中心
		int c = -1;
		int r = -1;
		int[] pArr = new int[chars.length];
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < chars.length ; i++){
			pArr[i] = r > i ? Math.min(pArr[2*c-i], r-i) : 1;
			while(i+ pArr[i] < chars.length && i - pArr[i] > -1){
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
			
			max = Math.max(max, i+pArr[i]);
		}
		return max - 1;
		
	}

	private static char[] manacherStr(char[] chars) {
		char[] nChar = new char[chars.length + 1];
		int idx = 0;
		for(int i =0;i < chars.length ;i++){
			nChar[i] = (i&1) == 0 ? '#' : chars[idx++];
		}
		return nChar;
	}
}
