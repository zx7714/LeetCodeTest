package com.csg.practice.D5M4;

/**
 * 数组中所有数都异或起来的结果，叫做异或和
 * 给定一个数组arr，返回arr的最大子数组异或和
 */
public class MaxXorOfArray {
    public static class Node {
        Node[] nodes = new Node[2];
    }

    static class NumTrim {
        Node node = new Node();

        void add(int num) {
            Node cur = node;
            // 从高位开始
            for (int i = 31; i >= 0; i--) {
                int path = num >> 31 & 1;
                cur.nodes[path] = cur.nodes[path] == null ? new Node() : cur.nodes[path];
                cur = cur.nodes[path];
            }
        }

        int numXor(int num) {
            Node cur = node;
            int ans = 0;
            for (int i = 31; i >= 0 ; i--) {
                // 取出第i为的状态
                int path = num >> i & 1;
                // 期望遇到的数，符号位期望遇到与自己相同的，异或结果为0保证是正数
                int best = i == 31 ? path : (path ^ 1);
                // 实际遇到的
                best = cur.nodes[best] != null ? best : (best ^ 1);
                // 第i位置的异或结果，放到ans中
                ans |= (path ^ best) << i;
                cur = cur.nodes[best];
            }
            return ans;
        }

    }

    public static int maxXorSubArr(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        NumTrim numTrim = new NumTrim();
        int xor = 0;
        // 一个数也没有的时候的异或和
        numTrim.add(0);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 0 到 i 的异或和
            xor ^= nums[i];
            // 找与xor最大的异或和， 0 ~ x 的异或和 ^ 0 ~ i 的异或和 = x ~ i 的异或和
            max = Math.max(max, numTrim.numXor(xor));
            numTrim.add(xor);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] num = new int[]{3, 10, 5, 25, 2, 8};
        int xor = maxXorSubArr(num);
        System.out.println(xor);
    }
}
