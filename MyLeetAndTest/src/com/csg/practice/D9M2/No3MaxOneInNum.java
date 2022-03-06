package com.csg.practice.D9M2;

/**
 * 给定一个范围a~b，和一个目标值target，求a~b范围内对应的二进制的 1 个数
 * 与target相等的数有几个
 */
public class No3MaxOneInNum {


    public static int getOne(long target) {
        int ans = 0;
        for (int i = 0; i < 64; i++) {
            ans += ((target >> i) & 1L) == 1 ? 1 : 0;
        }
        return ans;
    }

    public static long maxOne(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return 0;
        }
        if (start == 0 && end == 0) {
            return target == 0 ? 1 : 0;
        }
        int ehigh = 62;
        // 最高位1在哪个位置
        while ((end & (1L << ehigh)) == 0) {
            ehigh--;
        }
        if (start == 0) {
            return process(ehigh, target, 0, end);
        } else {
            int shigh = 62;
            while ((start & (1L << shigh)) == 0) {
                shigh--;
            }
            return process(ehigh, target, 0, end) - process(shigh, target, 0, start);
        }
    }

    public static long process(long index, int rest, int less, long num) {
        if (rest > index + 1) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (less == 1) {
            if (rest == index + 1) {
                return 1L;
            }
            return process(index - 1, rest - 1, 1, num) + process(index - 1, rest, 1, num);
        } else {
            if (rest == index + 1) {
                return (num & (1L << index)) == 0 ? 0 : process(index - 1, rest - 1, 0, num);
            } else {
                if ((num & (1L << index)) == 0L) {
                    return process(index - 1, rest, 0, num);
                } else {
                    return process(index - 1, rest - 1, 0, num) + process(index - 1, rest, 1, num);
                }
            }
        }
    }

    public static long maxOne2(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return 0;
        }
        if (start == 0 && end == 0) {
            return target == 0 ? 1 : 0;
        }
        int ehight = 62;
        while ((end & (1L << ehight)) == 0) {
            ehight--;
        }
        long dp[][][] = fillDp(ehight, 2, target);
        if (start == 0) {
            return process2(ehight, target, 0, end, dp);
        } else {
            int shight = 62;
            while ((start & (1L << shight)) == 0) {
                shight--;
            }
            long[][][] sdp = fillDp(shight, 2, target);
            return process2(ehight, target, 0, end, dp) - process2(shight, target, 0, start, sdp);
        }
    }

    public static long[][][] fillDp(int index, int less, int target) {
        long dp[][][] = new long[index + 1][less][target + 1];
        for (int i = 0; i <= index; i++) {
            for (int j = 0; j < less; j++) {
                for (int k = 0; k <= target; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return dp;
    }

    public static long process2(int index, int rest, int less, long num, long[][][] dp) {
        if (rest > index + 1) {
            return 0;
        }
        if (rest == 0) {
            return 1L;
        }
        if (dp[index][less][rest] != -1) {
            return dp[index][less][rest];
        }
        long ans = 0;
        if (less == 1) {
            if (rest == index + 1) {
                ans = 1;
            } else {
                ans = process2(index - 1, rest - 1, 1, num, dp) + process2(index - 1, rest, 1, num, dp);
            }
        } else {
            if (rest == index + 1) {
                ans = (num & (1L << index)) == 0 ? 0 : process2(index - 1, rest - 1, 1, num, dp);
            } else {
                if ((num & (1L << index)) == 0) {
                    ans = process2(index - 1, rest, 1, num, dp);
                } else {
                    ans = process2(index - 1, rest - 1, 0, num, dp) + process2(index - 1, rest - 1, 1, num, dp);
                }
            }
        }
        dp[index][less][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {
        long a = 1;
        System.out.println(getOne(a));
        long b = 3;
        System.out.println(getOne(b));
        long l = maxOne(2, 7, 2);
        System.out.println(l);
        long l1 = maxOne2(2, 7, 2);
        System.out.println(l1);
    }
}
