package com.csg.leetcode.leet.binary_tree;

/**
 * 二叉树的最大深度
 * @author zhangxu
 *
 */
public class MaxDeepOfTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	public int maxDepth(TreeNode root) {
		return process(root,0);
    }
	public int process(TreeNode node,int deep) {
		if(node == null) {
			return deep;
		}
		
		int left = process(node.left, deep+1);
		int right = process(node, deep+1);
		return Math.max(left, right);
	}
}
