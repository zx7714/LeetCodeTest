package com.csg.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一颗多叉树，其中部分叶子节点为描黑的节点，构造路径含有描黑节点新多叉树
 */
public class RetainTree {

    public boolean process(Tree tree,Tree newTree) {
        if(tree == null ) {
            return false;
        }

        if (tree.trees == null || tree.trees.size() ==0) {
            return tree.black;
        }

        List<Tree> curTress = new ArrayList<>();
        List<Tree> trees = tree.trees;
        for (Tree t : trees) {
            Tree curTree = new Tree();
            boolean process = process(t,curTree);
            if (process) {
                curTree.value = t.value;
                curTree.black = true;
                curTress.add(curTree);
            }
        }
        newTree.trees = curTress;
        return curTress.size() == 0;

    }

    public static void main(String[] args) {

    }

}

class Tree {
    int value;
    boolean black;
    List<Tree> trees;
}