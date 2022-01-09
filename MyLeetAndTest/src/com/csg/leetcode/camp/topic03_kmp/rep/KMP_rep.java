package com.csg.leetcode.camp.topic03_kmp.rep;

public class KMP_rep {
	public static int kmp(String s,String m){
		if(s == null || m == null || s.length() == 0 || m.length() == 0 || m.length() > s.length()){
			return 0;
		}
		char[] str = s.toCharArray();
		char[] match = m.toCharArray();
		int[] next = getNextArry(match);
		int x = 0;
		int y = 0;
		while(x < str.length && y < match.length){
			if(str[x] == match[y]){
				x++;
				y++;
			}else if(next[y] == -1){
				x++;
			}else{
				y = next[y];
			}
		}
		
		return y== match.length ? x-y : -1;
	}

	private static int[] getNextArry(char[] match) {
		if(match.length == 1){
			return new int[]{-1};
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int cn = 0;
		int i = 2; //从match数组的2号位置开始
		while(i < match.length){
			if(match[cn] == match[i-1]){
				next[i++] = ++cn;
			}else if(cn > 0){
				cn = next[cn];
			}else{
				next[i++] = 0;
			}
		}
		return next;
	}
	
	public static void main(String[] args) {
		String str1 = "1231231234445";
		String str2 = "1234445";
		System.out.println(kmp(str1, str2));
	}
}
