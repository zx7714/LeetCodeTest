package com.csg.leetcode.camp.topic03_kmp.rep;

public class Kmp2 {

    public static int kmp(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] next = getHelpArr(s2);
        int x = 0;
        int y = 0;

        while (x < s1.length && y < s2.length) {
            if (s1[x] == s2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == s2.length ? x - y : -1;
    }

    public static int[] getHelpArr(char[] s2) {
        int[] help = new int[s2.length];
        help[0] = -1;
        help[1] = 0;

        int cn = 0;
        int i = 2;

        while (i < help.length) {
            if (help[i - 1] == help[cn]) {
                help[i++] = ++cn;
            } else if (cn > 0) {
                cn = help[cn];
            } else {
                help[i++] = 0;
            }
        }
        return help;
    }
}
