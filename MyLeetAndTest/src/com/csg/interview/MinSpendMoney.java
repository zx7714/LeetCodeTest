package com.csg.interview;

/**
 * 从n到1选择一个数字
 * 猜选择了哪个数字。
 * 如果猜到正确的数字，就会赢得游戏
 * 如果你猜错了，那我会告诉你，选的数字比你的大或者小，并且需要继续猜
 * 每当你猜了数字x并且猜错的时候，你需要支付金额为x的现金
 * 如果你花光了钱，就会输掉游戏
 * 给你一个特定的数字n，返回能够 确保你获胜的最小金额，不管我选择那个数字。
 */
public class MinSpendMoney {
    public static int minMoney(int n) {
        return process(1, n);
    }

    public static int process(int L, int R) {
        if (L == R) {
            return 0;
        }
        if (L == R - 1) {
            return L;
        }
        // L + process(L + 1, R) 选了L需要承受的金额
        // R + process(L, R - 1) 选了R需要承受的金额
        int min = Math.min( L + process(L + 1, R),  R + process(L, R - 1));
        for (int i = L + 1; i < R; i++) {

            min = Math.min(min,
                    // 确保获胜，所以认为每次选都不对
                    Math.max(
                            // 选择i后，正确数字在i的右边
                            i + process(L + 1, i - 1),
                            // 正确数字在i的左边
                            i + process(i + 1, R)
                    )
            );
        }
        return min;
    }

    public int minMoneyDp(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1;i<=n;i++) {
            dp[i][i+1] = i;
        }
        for(int L = n -2;L >= 1;L--){
            for (int R = L+2 ; R <= n;R++) {
                int min = Math.min( L + dp[L + 1][R],  R + dp[L][R - 1]);
                for (int i = L + 1; i < R; i++) {
                    min = Math.min(min, Math.max(i + dp[L + 1][i - 1], i + dp[L + 1][i - 1]));
                }
            }
        }
        return dp[1][n];
    }

}
