package com.csg.leetcode.leetcode.node;

public class ZuiChangWuChongFuZiChuan {
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int dp = 1;
        int[] help = new int[256];
        for(int i = 0;i< 256;i++) {
            help[i] = -1;
        }
        help[s.charAt(0)] = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < str.length;i++) {
            dp = Math.min(dp + 1,i - help[str[i]]);
            help[str[i]] = i;
            max = Math.max(max,dp);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "au";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
