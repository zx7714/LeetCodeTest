package com.csg.leetcode;
/**整数翻转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	示例 1:
	输入: 123
	输出: 321
	
	示例 2:	
	输入: -123
	输出: -321
	
	示例 3:
	输入: 120
	输出: 21
    注意:
     假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @author ZhangXuAo
 *
 */
public class ReverseInteger {
	public static void main(String arg[]){
		int a = -1234567899;
		int result = new ReverseInteger().reverseInterer(a);
		System.out.println(result);
	}
	public int reverseInterer(int x){
		int result = 0;
		while(x!=0){
			int pop =  x%10;//每次取最后一位
			if(result>Integer.MAX_VALUE/10 || (result==Integer.MAX_VALUE/10 && pop>7)) //出现result>Integer.MAX_VALUE/10还有pop添加时一定溢出了
				return 0;
			if(result<Integer.MIN_VALUE/10 || (result==Integer.MIN_VALUE/10 && pop<-8))
				return 0;
			result = result*10+pop;//每次乘10加最后一位。
			x/=10;
		}
		return result;
	}
}
