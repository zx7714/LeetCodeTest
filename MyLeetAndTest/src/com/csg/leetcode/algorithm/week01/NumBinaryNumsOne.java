package com.csg.leetcode.algorithm.week01;
/**
 * 一个整数，找它的二进制数中1的个数
 * @author zhangxu
 *
 */
public class NumBinaryNumsOne {
	
	public static void main(String[] args) {
		int num = 15;
		find(num);
	}
	// 0001 -> 1110 -> 1111
	public static void find(int num) {
		int digt = 0;
		int count = 0;
		while(num !=0) {
			digt = (num & (~num + 1));
			num ^= digt;
			count ++;
		}
		System.out.println(count);
	}
}
