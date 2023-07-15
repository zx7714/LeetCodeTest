package com.csg.leetcode.camp.topic03_kmp.rep;

public class Kmp {

    public static int kmp (String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[] help = getHelpArr(s2);

        int x = 0;
        int y = 0;

        while (x < s1.length && y < s2.length) {
            if (s1[x] == s2[y]) {
                x++;
                y++;
            } else if (help[y] == -1) {
                x++;
            } else {
                y = help[y];
            }
        }
        return y == s2.length ? x - y : -1;
    }


    public static int[] getHelpArr(char[] str2) {
        int [] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else  {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "abccds";
        String str2 = "bc";

        System.out.println(kmp(str1,str2));

    }
}
