package com.csg.leetcode.algorithm.week04;

/**
 * 给定一棵二叉树中的节点，找到这个节点的后继节点
 * ps:后继节点是指二叉树中序遍历的下一个节点
 * @author zhangxu
 *
 */
public class SuccessorNode {
	public static class Node{
		int value;
		Node left;
		Node right;
		Node parent;
		
		public Node(int data) {
			this.value = data;
		}
	}
	public static Node successor(Node node) {
		/**
		 * 1.如果右树不为空，则找到右树的最左的节点
		 * 2.右树为找父节点。
		 *   1)是父节点的左孩子，是的话后继节点就是父节点
		 *   2)是父节点的右孩子，找父节点的父节点，重复1) 2)
		 */
		if(node.right != null) {
			return mostLeftNode(node.right);
		}else {
			Node parent = node.parent;
			while(parent != null && node != parent.left) {
				node = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	private static Node mostLeftNode(Node node) {
		if(node == null) {
			return null;
		}
		
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + successor(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + successor(test));
	}
}
