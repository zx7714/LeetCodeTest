package com.csg.practice.D7M2;

import com.csg.leetcode.algorithm.week04.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树A是否存在一个子树的结构为二叉树B
 */
public class No3BinaryTreeContainSon {
    public static int containBinary(Tree tree, Tree tree2) {
        if (null == tree || null == tree2) {
            return -1;
        }
        List<String> s1 = getStrList(tree);
        List<String> s2 = getStrList(tree2);
        int next[] = generateKmpStr(s2);
        int i = 0;
        int j = 0;
        while (i < s1.size() && j < s2.size()) {
            if (s1.get(i).equals(s2.get(j))) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == s2.size() ? i - j : -1;
    }

    private static int[] generateKmpStr(List<String> s2) {
        int[] next = new int[s2.size()];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < s2.size()) {
            if (s2.get(i - 1).equals(s2.get(cn))) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    public static List<String> getStrList(Tree tree) {
        List<String> list = new ArrayList<>();
        tree(tree, list);
        return list;
    }

    public static void tree(Tree tree, List<String> list) {
        if (null == tree) {
            list.add("null");
        } else {
            list.add(String.valueOf(tree.value));
            tree(tree.left, list);
            tree(tree.right, list);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree(1);
        tree.right = new Tree(2);
        tree.left = new Tree(3);
        tree.left.right = new Tree(4);
        tree.left.left = new Tree(5);

        Tree tree1 = new Tree(3);
        tree1.right = new Tree(4);

        int i = containBinary(tree, tree1);
        System.out.println(i);
    }
}
