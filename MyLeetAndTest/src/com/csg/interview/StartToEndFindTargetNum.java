package com.csg.interview;

/**
 * 限制：0 <= start <= end, 0 <= target <= 65
 * <p>
 * [start,end]范围上的数字，有多少数字二进制中1的个数等于target
 */
public class StartToEndFindTargetNum {

    public static int solute1(long start, long end, int target) {
        int ans = 0;
        while (start <= end) {
            if (bit(start) == target) {
                ans++;
            }
            start++;
        }
        return ans;
    }

    static int bit(long num) {
        int index = 1;
        int count = 0;
        while (num != 0) {
            if ((num & index) != 0) {
                count++;
            }
            index = index >> 1;
            num = num >> 1;
        }
        return count;
    }

    public static int solute2(long start, long end, int target) {
        if (start < 0 || end < 0 || end < start || target < 1) {
            return 0;
        }

        if (end == 0 && start == 0) {
            return target == 0 ? 1 : 0;
        }
        // 获取最高位的1
        int index = 62;
        while ((end & 1L << index) == 0) {
            index--;
        }
        if (start == 0) {
            return process1(index, 0, target, end);
        } else {
            start--;
            int startIndex = 62;
            while (startIndex > 0 && (start & 1L << startIndex) == 0) {
                startIndex--;
            }
            return process1(index, 0, target, end) - process1(index, 0, target, start);
        }

    }

    /**
     * 递归处理num数字对饮的二进制，即选择每一位填1或0，rest全部用完后所有num小的数的个数
     *
     * @param index 指向num当前二进制的位数,从最大位往最小位移动
     * @param less  前面所做的选择，是否小于num的前缀，小于 1，等于 0
     * @param rest  消耗1的个数，每次选择可以选择1或0，选择1则rest-1
     * @param num   指定数字
     * @return ans
     */
    public static int process1(int index, int less, int rest, long num) {
        // rest全部用完了，得到一种结果
        if (rest == 0) {
            return 1;
        }
        // num所剩余的二进制位数已经无法放下全部的rest
        if (rest > index + 1) {
            return 0;
        }
        // 所填的前缀比num的前缀小，可以选择填 1或填 0
        if (less == 1) {
            // 剩余的1 与后面的index相等，只能全为1
            if (rest == index + 1) {
                return 1;
            }
            return process1(index - 1, 1, rest, num) + process1(index - 1, 1, rest - 1, num);
        }
        // 所填前缀和num的前缀相等，就要判断能否填1，保证填的值不能大于num
        else {
            // 剩余的1 与后面的index相等,判断num对应的index能否为1，及判断填1后是否比num大？
            if (rest == index + 1) {
                return (num & (1L << index)) == 0 ? 0 : process1(index - 1, 0, rest - 1, num);
            }
            // 判断当前位置能不能填1
            if ((num & 1L << index) == 0) {
                return process1(index - 1, 0, rest, num);
            } else {
                return process1(index - 1, 1, rest, num) + process1(index - 1, 0, rest - 1, num);
            }
        }
    }
    // 记忆化搜索
    public static int process1(int index, int less, int rest, long num, int[][][] dp) {
        if (rest == 0) {
            return 1;
        }
        if (rest > index + 1) {
            return 0;
        }
        if (dp[index][less][rest] != -1) {
            return dp[index][less][rest];
        }
        int ans;
        // 所填的前缀比num的前缀小，可以选择填 1或填 0
        // 所填的前缀比num的前缀小，可以选择填 1或填 0
        if (less == 1) {
            // 剩余的1 与后面的index相等，只能全为1
            if (rest == index + 1) {
                ans = 1;
            } else {
                ans = process1(index - 1, 1, rest, num, dp) + process1(index - 1, 1, rest - 1, num, dp);
            }
        }
        // 所填前缀和num的前缀相等，就要判断能否填1，保证填的值不能大于num
        else {
            // 剩余的1 与后面的index相等,判断num对应的index能否为1，及判断填1后是否比num大？
            if (rest == index + 1) {
                ans = (num & (1L << index)) == 0 ? 0 : process1(index - 1, 0, rest - 1, num, dp);
            } else {
                // 判断当前位置能不能填1
                if ((num & 1L << index) == 0) {
                    ans = process1(index - 1, 0, rest, num, dp);
                } else {
                    ans = process1(index - 1, 1, rest, num, dp) + process1(index - 1, 0, rest - 1, num, dp);
                }
            }
        }
        dp[index][less][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int start = 1, end = 5, target = 1;
        int i = solute1(start, end, target);
        int j = solute2(start, end, target);
        System.out.println(i == j);
    }
}
