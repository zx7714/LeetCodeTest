package com.csg.leetcode.camp.topic07Manacher.rep;

public class Manacher {
    public int manacher(String str) {
        char[] help = getManacherStr(str.toCharArray());
        int C = -1;
        int R = -1;
        int[] pArr = new int[help.length];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < help.length ; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            while (i + pArr[i] < help.length && i - pArr[i] > -1) {
                if (help[i + pArr[i]] == help[i - pArr[i]]) {
                    pArr[i] ++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }

        return max - 1;
    }

    public char[] getManacherStr(char[] s) {
        char[] help = new char[2*s.length + 1];
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            help[i] = (i & 1) == 0 ? '#' : s[index++];
        }
        return help;
    }
}
