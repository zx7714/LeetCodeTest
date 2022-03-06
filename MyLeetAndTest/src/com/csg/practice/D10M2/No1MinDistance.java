package com.csg.practice.D10M2;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * int n,int[][] roads,int x,int y n表示城市数量，城市编号 0 ~ n - 1,
 * roads[i][j] == distance 表示城市 i 到 j 的距离为distance（无向图）。
 * 求城市 x 到城市 y 的距离。
 */
public class No1MinDistance {
    // roads 表示 {1,2,3} 表示 1到2的距离为3
    public static int minDistance(int n, int[][] roads, int x, int y) {
        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        // 构造图
        for (int[] road : roads) {
            map[road[0]][road[1]] = Math.min(map[road[0]][road[1]], road[2]);
            map[road[1]][road[0]] = Math.min(map[road[1]][road[0]], road[2]);
        }
        // 小跟堆
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        boolean[] compute = new boolean[n + 1];
        queue.add(new Node(x, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 已经计算过了
            if (compute[cur.x]) {
                continue;
            }
            // 取到目标
            if (cur.x == y) {
                return cur.distance;
            }
            compute[cur.x] = true;
            for (int i = 1; i <= n; i++) {
                // 不计算自己 && 当前位置到i位置有路 && 没有计算过
                if (cur.x != i && map[cur.x][i] != Integer.MAX_VALUE && !compute[i]) {
                    queue.add(new Node(i, cur.distance + map[cur.x][i]));
                }
            }
        }
        return Integer.MAX_VALUE;
    }


    static class Node {
        int x;
        int distance;

        Node(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        int[][] d =
        {
            {1,2,3},
            {2,3,4},
            {2,5,6},
            {5,3,10}
        };
        int i = minDistance(5,d,1,5);
        System.out.println(i);
    }
}
