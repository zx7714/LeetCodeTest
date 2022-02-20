package com.csg.practice.D7M2;

/**
 * 给定一个字符串A和字符串B，判断B是否为A的子串,返回B的位置
 */
public class No1KMP {

    public static int kmp(String s,String goal) {
        if (null == s || null == goal || goal.length() > s.length()) {
            return -1;
        }
        char[] s1 = s.toCharArray();
        char[] s2 = goal.toCharArray();
        int[] next = generateKmpStr(s2);

        int index = 0;
        int point = 0;
        while(index <s1.length && point < s2.length) {
            if (s1[index] == s2[point]) {
                index++;
                point++;
            } else if (next[point] == -1) {
                index++;
            } else {
                point = next[point];
            }
        }
        return point == s2.length ? index -point : -1;
    }
    // 找出i-1位置，字符前缀与后缀想通的字符数
    private static int[] generateKmpStr(char[] arr) {
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int index = 0;
        int i = 2;
        while (i < next.length) {
            if(arr[i-1] == arr[index]) {
                next[i++] = ++index;
            }else if (index > 0) {
                index = next[index];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "12312312344451";
        String str2 = "12344451";
        int kmp = kmp(str1, str2);
        System.out.println(kmp);
    }
}
