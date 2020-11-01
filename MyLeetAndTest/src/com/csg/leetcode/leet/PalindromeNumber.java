package com.csg.leetcode.leet;
/**回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
	示例 1:
	
	输入: 121
	输出: true
	示例 2:
	
	输入: -121
	输出: false
	解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
	示例 3:
	
	输入: 10
	输出: false
	解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * @author ZhangXuAo
 *
 */
public class PalindromeNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 1221;
		boolean res = new PalindromeNumber().palindromeNumberLeetCode(a);
		System.out.println(res);
	}
	public boolean palindromeNumberMyAnswer(int x){
		if(x == 0) 
			return true;
		if(x < 0)  
			return false;
		int palindNum = 0;//比较翻转数
		int mid = x;
		while(mid != 0){
			int count = mid%10;
			palindNum = palindNum*10+count;
			mid/=10;
		}
		if(x==palindNum)
			return true;
		else 
			return false;
	}
	public boolean palindromeNumberLeetCode(int x){
		if(x<0 || (x>0 && 0==x%10)){
			return false;
		}
		int palindNum = 0;
		while(x>palindNum){//回文数对称
			palindNum = palindNum*10+x%10;
			x/=10;
		}
		if(palindNum==x || palindNum/10 == x)
			return true;
		return false;
	}
}
