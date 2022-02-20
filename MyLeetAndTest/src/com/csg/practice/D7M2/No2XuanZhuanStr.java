package com.csg.practice.D7M2;

/**
 * 字符串S1是否为字符串s2的旋转字符串
 */
public class No2XuanZhuanStr {
    public static int xuanzhuan(String s,String goal) {
        if (null == s || null == goal || s.length() != goal.length()) {
            return -1;
        }

        char[] s1 = (s + s).toCharArray();
        char[] s2 = goal.toCharArray();
        int next[] = generateKmpStr(s2);
        int i = 0;
        int j = 0;

        while (i < s1.length && j < s2.length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == s2.length ? i - j : -1;
    }
    // aaabc
    private static int[] generateKmpStr(char[] s2) {
        int[] next = new int[s2.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int index = 2;
        while (index < s2.length) {
            if (s2[index -1] == s2[cn]) {
                next[index++] = ++cn;
            }else if (cn > 0) {
                cn = next[cn];
            }else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "123456";
        String s2 = "2345621";
        int i = (s1+s1).indexOf(s2);
        System.out.println(i);
        int xuanzhuan = xuanzhuan(s1, s2);
        System.out.println(xuanzhuan);
    }
}
