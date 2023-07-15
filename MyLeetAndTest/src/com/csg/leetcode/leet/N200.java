package com.csg.leetcode.leet;

public class N200 {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    process(grid, i, j, m, n);
                }
            }
        }
        return ans;
    }

    public void process(char[][] grid, int i, int j, int m, int n) {
        if (grid[i][j] == '0' || i >= m || j >= n || i < 0 || j < 0) {
            return;
        }
        grid[i][j] = '0';
        process(grid, i - 1, j, m, n);
        process(grid, i + 1, j, m, n);
        process(grid, i, j - 1, m, n);
        process(grid, i, j + 1, m, n);
    }

}
