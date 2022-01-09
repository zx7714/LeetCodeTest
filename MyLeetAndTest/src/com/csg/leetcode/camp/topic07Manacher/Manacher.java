package com.csg.leetcode.camp.topic07Manacher;

/**
 * Manacher算法，求最长回文子串
 * @author zhangxu
 *
 */
public class Manacher {
	public static int manancher(String s){
		if(null == s || s.length() == 0){
			return 0;
		}
		
		char[] str = manacherString(s);
		//中心
		int C = -1;
		//以C为中心回文的边界
		int R = -1;
		//每个位置对应的回文半径大小
		int[] pArr = new int[str.length];
		int max = Integer.MIN_VALUE;
		for(int i = 0;i<str.length;i++){
			// i时是否在R内
			pArr[i] = R > i ?
					//i'在R内，i'就是i的答案；i'在R外，i-R的距离，是至少不用验的；i
					//pArr[2*C - i] >= R-i , i' 的回文半径延伸到了R外;
					//pArr[2*C - i] < R-i ,i' 的回文半径在R内
					//2*C - i就是i的对称点
					Math.min(pArr[2*C - i],R-i) : 
					//i在R外，只要不用验证的情况，它自己肯定是回文
					1;
			//向外扩张
			while(i+pArr[i] < str.length && i - pArr[i] > -1){
				//扩张
				if(str[i + pArr[i]] == str[i - pArr[i]]){
					pArr[i]++;
				}else{
					break;
				}
			}
			//i扩张的范围超过了R
			if(i + pArr[i] > R){
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		
		return max - 1;
		
	}

	private static char[] manacherString(String s) {
		char[] sArr = s.toCharArray();
		char[] newArr = new char[sArr.length*2 +1];
		int index = 0;
		//偶数的位置补'#'
		for(int i = 0;i < newArr.length;i++){
			newArr[i] = (i&1) == 0 ? '#' : sArr[index++];
		}
		return newArr;
	}
	
	public static void main(String[] args) {
		String str = "abcbaccabccba";
		int ans = manancher(str);
		System.out.println(ans);
	}
}
