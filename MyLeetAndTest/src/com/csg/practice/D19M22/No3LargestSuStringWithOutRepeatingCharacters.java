package com.csg.practice.D19M22;

/**
 * 最长无重复子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 动态规划思路：
 * 1. i-1位置推了多少长度
 * 2. 上次出现 [i]字符串的是哪个位置
 */
public class No3LargestSuStringWithOutRepeatingCharacters {

    public static int longestSubstringWithOutRepeatingCharacters(String str){
        if (null == str || str.length()==0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        // i - 1位置推了多长
        int dp = 1;
        // 记录i位置字符上次出现的位置
        int[] help = new int[256];
        for (int index = 0 ; index < 256;index ++) {
            help[index] = -1;
        }
        // 第一个位置
        help[str.charAt(0)] = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1;i < str.length();i++) {
            dp = Math.min(dp + 1,i - help[str.charAt(i)]);
            help[str.charAt(i)] = i;
            max = Math.max(dp,max);
        }
        return max;
    }

    public static void main(String[] args) {
        int au = longestSubstringWithOutRepeatingCharacters("a  u");
        System.out.println(au);
    }
}
