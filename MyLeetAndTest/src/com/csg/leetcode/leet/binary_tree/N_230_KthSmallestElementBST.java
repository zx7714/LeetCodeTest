package com.csg.leetcode.leet.binary_tree;

import com.csg.leetcode.leet.binary_tree.MaxDeepOfTree.TreeNode;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 
 * 说明： 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * 
 * @author zhangxu
 *
 */
public class N_230_KthSmallestElementBST {
	 public static int kthSmallest(TreeNode root, int k) {
		 int[] arr = new int[2];
		 in(root,arr,k);
		 return arr[1];
	 }

	private static void in(TreeNode root, int[] arr,int k) {
		if(root==null) {
			return;
		}
		in(root.left,arr,k);
		if(arr[0] < k) {
			arr[0]++;
			arr[1] = root.val;
		}
		//System.out.println(root.val);
		in(root.right, arr, k);
	}
	public static void main(String[] args) {
		TreeNode head = new TreeNode(3);
		head.left = new TreeNode(1);
		head.left.right = new TreeNode(2);
		
		head.right = new TreeNode(4);
		
		System.out.println(kthSmallest(head,1));
	}
}
