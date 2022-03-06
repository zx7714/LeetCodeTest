package com.csg.practice.D8M2;

/**
 * 最长回文子串
 */
public class Manacher {

    public static int manacher(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = generateManacher(s);
        // 最大回文半径
        int R = -1;
        // 最大回文半径对应的回文中心
        int C = -1;
        // 帮助数组，记录i位置对应的回文半径
        int[] help = new int[str.length];
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < str.length;i++) {
            help[i] = R > i ? Math.min(help[2*C -i],R-i) : 1;
            while (i + help[i] < str.length && i - help[i] > -1) {
                if (str[i + help[i]] == str[i - help[i]]) {
                    help[i]++;
                }else {
                    break;
                }

                if (i + help[i] > R) {
                    R = i + help[i];
                    C = i;
                }
                max = Math.max(max,help[i]);
            }
        }
        return max - 1;
    }

    public static char[] generateManacher(String str) {
        char[] help = new char[2*str.length() +1];
        int index = 0;
        for (int i = 0;i < help.length;i++) {
            help[i] = (i & 1) == 0 ? '#' : str.charAt(index++);
        }
        return help;
    }

    public static void main(String[] args) {
        String s = "1233213";
        int manacher = manacher(s);
        System.out.println(manacher);
    }
}
