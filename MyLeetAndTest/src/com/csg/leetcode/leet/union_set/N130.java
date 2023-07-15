package com.csg.leetcode.leet.union_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class N130 {

    public static void solve(char[][] board) {
        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int col = board.length;
        int row = board[0].length;
        UnionSet us = new UnionSet(col * row + 1);
        int dummyNum = col * row;

        Map<Integer, Integer> tmp = new HashMap<>();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (board[i][j] != 'O') {
                    continue;
                }
                if (i == 0 || j == 0 || i == col - 1 || j == row - 1) {
                    us.union(getIndex(row, i, j), dummyNum);
                }
                if (i != 0 && board[i - 1][j] == 'O') {
                    us.union(getIndex(row, i, j), getIndex(row, i - 1, j));
                }
                if (j != 0 && board[i][j - 1] == 'O') {
                    us.union(getIndex(row, i, j), getIndex(row, i, j - 1));
                }
                tmp.put(i,j);
            }
        }
    }

    static class UnionSet {
        public int[] parent;

        public UnionSet(int len) {

            parent = new int[len];
            for (int i = 0; i < len; i++) {
                parent[i] = i;
            }
        }

        public int findParent(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int xParent = findParent(x);
            int yParent = findParent(y);
            if (xParent != yParent) {
                parent[xParent] = yParent;
            }
        }

        public boolean sameSet(int x, int y) {
            return findParent(x) == findParent(y);
        }
    }

    static int getIndex(int col, int i, int j) {
        return i * col + j;
    }

    public static void main(String[] args) {
//        char[][] board = {{'X', 'X', 'X', 'X' }, {'X', 'O', 'O', 'X' }, {'X', 'X', 'O', 'X' }, {'X', 'O', 'X', 'X' }};
//        char[][] board = {{'O', 'O' }, {'O', 'O' }};
        char[][] board = {{'O', 'O', 'O' }, {'O', 'O', 'O' }, {'O', 'O', 'O' }};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===");
        solve(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
