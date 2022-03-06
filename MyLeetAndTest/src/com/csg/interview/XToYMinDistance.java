package com.csg.interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * int n,int[][] roads,int x int y
 * n表示城市数量，城市编号0~n-1
 * road[i][j] == distance,表示城市i到城市j距离为distance（无向图）
 * [
 * [1,2,8], // 表示1城市到2城市的距离为8
 * [2,4,3] // 表示2城市到4城市的距离为3
 * ]
 * 求x城市到y城市的最短路径
 */
public class XToYMinDistance {

    public int solute(int n, int[][] roads, int x, int y) {
        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; n++) {
            for (int j = 0; i < n + 1; n++) {
                if (i == j)
                    map[i][j] = 0;
                else
                    map[i][j] = Integer.MAX_VALUE;
            }
        }
        /**
         * 构造邻接图
         * [
         *   [0,2......n],
         *   [3,0......n],
         *   [2,5......n]
         * ]
         * map[a,b]=c 表示 a到b的距离为 c，ex map[1][2] = 2 表示 1到2的距离为2
         */
        for (int[] road : roads) {
            map[road[0]][road[1]] = Math.min(map[road[0]][road[1]], road[2]);
            map[road[1]][road[2]] = Math.min(map[road[1]][road[0]], road[2]);
        }

        // 记录已经计算出最小距离的路
        boolean[] computed = new boolean[n+1];
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.path));
        heap.add(new Node(x,0));
        while(!heap.isEmpty()) {
            Node cur = heap.poll();
            if (cur.city == y) {
                return cur.path;
            }
            computed[cur.city] = true;
            // 记录当前城市连接的所有城市的路径
            for (int next = 1 ; next < n+1 ;next ++) {
                // 连接的下个城市不是当前城市 && 当前城市到下个城市的不是无穷大（即有路线连接） && 下个城市没有计算过
                if (cur.city != next && map[cur.city][next] != Integer.MAX_VALUE && !computed[next]) {
                    heap.add(new Node(next,cur.path + map[cur.city][next]));
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    class Node {
        int city;
        int path;
        Node(int city,int path) {
            this.city = city;
            this.path = path;
        }
    }
}
