package com.csg.practice.D9M2;

/**
 * 给定一颗二叉树，其中有节点染红了，返回含有染红节点的所有路径
 */
public class No1GetRedTree {

    public static Tree getReadTree(Tree tree) {
        process(tree);
        return tree;
    }
    public static boolean process(Tree tree) {
        if (tree == null) {
            return false;
        }
        boolean left = process(tree.left);
        boolean right = process(tree.right);
        if (!left) {
            tree.left = null;
        }
        if (!right) {
            tree.right = null;
        }
        return left || right || tree.color;
    }

    static class Tree {
        int value;
        boolean color;
        Tree right;
        Tree left;
        public Tree(int value,boolean color) {
            this.value = value;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        Tree head = new Tree(1,false);
        head.left = new Tree(2,false);
        head.right = new Tree(3,false);
        head.left.left = new Tree(4,true);
        head.left.right = new Tree(5,false);
        head.right.left = new Tree(6,false);
        boolean process = process(head);
        System.out.println(process);
    }
}
