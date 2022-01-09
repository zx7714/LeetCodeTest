package com.csg.leetcode.camp.topic03_kmp;
/**
 * 给定一个字符串str1，str1有很多旋转词，
 * 例如 12345的旋转词有 12345、23451、34512、45123、51234，
 * 再给定一个字符串str2，判断str2是否是str1的旋转词。
 * @author zhangxu
 *
 */
public class RevolveWord {
	//kmp算法
	public static boolean word(String s1,String s2){
		if(s1 == null || s2 == null || s1.length() == 0 || 
				s2.length() == 0 || s1.length() != s2.length()){
			return false;
		}
		//两个字符串拼接包含所有的旋转词
		char[] str = (s1 + s1).toCharArray();
		char[] match = s2.toCharArray();
		int[] next = getNextArray(match);
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
		return y == match.length;
	}

	private static int[] getNextArray(char[] match) {
		if(match.length == 1){
			return new int[]{-1};
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int index = 2;
		int cn = 0;
		while(index < match.length){
			if(match[index - 1] == match[cn]){
				next[index++] = ++cn;
			}else if(cn > 0){
				cn = next[cn];
			}else{
				next[index++] = 0;
			}
		}
		return next;
	}
	
	public static void main(String[] args) {
		String str1 = "123456"; //123456123456
		String str2 = "456123";
		System.out.println(word(str1, str2));
	}
}
