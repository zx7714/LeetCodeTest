package com.csg.leetcode.camp.topic07Manacher.rep;

public class AddCharToEndToBuildPalindromicStr {
	
	public static int manacher(String s){
		if(s == null || s.length() == 0){
			return 0;
		}
		char[] sChar = manacherString(s);
		
		int max = 0;
		int R = -1;
		int C = -1;
		int[] pArr = new int[sChar.length];
		
		for(int i = 0 ;i < sChar.length;i++){
			pArr[i] = R > i ? Math.min(pArr[2 * C - i ],i-R) : 1;
			while(i+pArr[i] < sChar.length && i -pArr[i] > -1){
				if(sChar[i+pArr[i]] == sChar[i - pArr[i]]){
					pArr[i]++;
				}else{
					break;
				}
			}
			if(i > R){
				C = i;
				R = i  + pArr[i];
			}
			if(R == sChar.length){
				max = pArr[i];
				break;
			}
		}
		return s.length() - max + 1;
	}

	private static char[] manacherString(String s) {
		char[] sChar = s.toCharArray();
		char[] newChar = new char[sChar.length * 2 + 1];
		
		int index = 0;
		for(int i = 0 ; i < newChar.length;i++){
			newChar[i] = (i&1) == 0 ? '#' : sChar[index++];
		}
		return newChar;
	} 
	
	public static void main(String[] args) {
		String str1 = "abcd123321";
		System.out.println(manacher(str1));
	}
}	
