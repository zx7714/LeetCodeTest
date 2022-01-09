package com.csg.leetcode.camp.topic07Manacher.rep;

public class Manacher_Rep_1 {
	public static String manacher(String s){
		if(s== null || s.length() == 0){
			return s;
		}
		char[] sChar = manacherString(s);
		
		int C = -1;
		int R = -1;
		int[] pArr = new int[sChar.length];
		int max = Integer.MIN_VALUE;
		int start = 0;
		int end = 0;
		for(int i = 0;i<sChar.length;i++){
			pArr[i] = R > i ? Math.min(pArr[2*C - i],R-i) : 1;
			while(i + pArr[i] < sChar.length && i - pArr[i] > -1){
				if(sChar[i+pArr[i]] == sChar[i -pArr[i]]){
					pArr[i] ++;
				}else{
					break;
				}
				
				if(i > R){
					R = i + pArr[i];
					C = i;
				}
				if(pArr[i] > max){
					max = pArr[i];
					start = i - max+1;
					end = i + max;
				}
				
			}
		}
		return String.valueOf(sChar).substring(start, end).replace("#", "");
	}
	

	private static char[] manacherString(String s) {
		char[] sChar = s.toCharArray();
		char[] newChar = new char[s.length() * 2 + 1];
		int index = 0;
		for(int i = 0;i< newChar.length;i++){
			newChar[i] = (i&1) == 0 ? '#' : sChar[index++];
		}
		return newChar;
	}
	public static void main(String[] args) {
		String str = "babad";
		System.out.println(manacher(str));
	}
}
