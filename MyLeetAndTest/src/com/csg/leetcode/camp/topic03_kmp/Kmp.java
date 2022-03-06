package com.csg.leetcode.camp.topic03_kmp;
/**
 * 给定两个字符串str和match，判断match是否是str的子串，返回str中位置。
 * 
 * @author zhangxu
 *
 */
public class Kmp {
	public static int kmp(String str,String match){
		if(str==null || match == null ||str.length()==0 || match.length()==0 || 
				str.length() < match.length()){
			return 0;
		}
		char[] s = str.toCharArray();
		char[] m = match.toCharArray();
		int[] next = getNextArr(m);
		
		int x = 0;
		int y = 0;
		// A  A A A B
		//[-1 0 1 2 3]
		while(x < s.length && y < m.length){
			//x、y位置的字符串匹配上了
			if(s[x] == m[y]){
				x++;
				y++;
			}else if(next[y] == -1){ //没匹配上，y跳到头了都没有找到合适的
				x++;
			}else{ //往回跳
				y = next[y];
			}
		}
		//match走完了，表示匹配上了，x位置减去y就是第一个匹配的点
		return y == m.length ? x-y : -1;
		
	}

	private static int[] getNextArr(char[] m) {
		if(m.length == 1){
			return new int[]{-1};
		}
		int[] next = new int[m.length];
		next[0] = -1;
		next[1] = 0;
		// cn代表，cn位置的字符，是当前和i-1位置比较的字符;也代表某个字符前缀和后缀最长的相同长度。
		int cn = 0;
		int i = 2;
		// A A A B
		while(i < next.length){
			if(m[i-1] == m[cn]){
				next[i++] = ++cn;
			}else if(cn > 0){
				cn = next[cn]; // 相比较的字符不同， ，往前跳
			}else{
				next[i++] = 0;
			}
		}
		return next;
	}
	public static void main(String[] args) {
		String str1 = "1231231234445";
		String str2 = "12344451";
		System.out.println(kmp(str1, str2));
	}
}
