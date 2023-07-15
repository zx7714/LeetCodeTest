package com.csg.leetcode.leet.str;

/**
 * https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class N421MaximumXorOfTwoNumbersInArray {
    static class Node {
        Node[] nodes = new Node[2];
    }

    static class NumTrim {
        Node node = new Node();
        public void add(int num) {
            // 构造线段树
            Node cur = node;
            for (int i = 31; i >= 0; i--) {
                int index = num >> i & 1;
                cur.nodes[index] = cur.nodes[index] == null ? new Node() : cur.nodes[index];
                cur = cur.nodes[index];
            }
        }
        public int numXor(int num) {
            Node cur = node;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                // 取出num第i位的状态
                int path = num >> i & 1;
                // 期望遇到的值，符号位 期望遇到相同的值，相同值为异或结果为0，结果为正数
                int best = i == 31 ? path : (path ^ 1);
                // 实际能遇到的值
                best = cur.nodes[best] != null ? best : (best ^ 1);
                // path和best异或完的结果，左移i位，得出当前位的结果。
                ans |= (path ^ best) << i;
                cur = cur.nodes[best];
            }
            return ans;
        }
    }
    public static int findMaximumXOR(int[] nums) {
        if(null == nums || nums.length < 2){
            return 0;
        }
        NumTrim numTrim = new NumTrim();
        numTrim.add(nums[0]);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            max  =Math.max(max,numTrim.numXor(nums[i]));
            numTrim.add(nums[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] num = new int[] {3,10,5,25,2,8};
        int maximumXOR = findMaximumXOR(num);
        System.out.println(maximumXOR);
    }
}
