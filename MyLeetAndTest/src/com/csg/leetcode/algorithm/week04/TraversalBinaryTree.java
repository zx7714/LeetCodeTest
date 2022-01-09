package com.csg.leetcode.algorithm.week04;

import java.util.Stack;

public class TraversalBinaryTree {
	public void recursionTree(Tree tree) {
		f(tree);
	}
	/*
	 * 递归序列，从头往下，没有个节点都会经过三次。打印时机就是先中后遍历。
	 */
	public void f(Tree tree) {
		if(tree == null)
			return;
		//在这打印就是先序遍历
		System.out.println(tree.value);
		f(tree.left);
		//在这打印就是中序遍历
		System.out.println(tree.value);
		f(tree.right);
		//在这打印就是后续遍历
		System.out.println(tree.value);
	}
	/**
	 * 先序遍历，利用栈结构，头节点先入栈，从右孩子先入栈左孩子再入栈，弹出打印
	 * @param tree
	 */
	public static void printTreePre1(Tree tree) {
		if(tree == null) {
			return;
		}
		System.out.print("pre-order :");
		Stack<Tree> stack = new Stack<Tree>();
		stack.add(tree);
		while(!stack.isEmpty()) {
			tree = stack.pop();
			System.out.print(" "+ tree.value);
			if(tree.right != null) {
				stack.push(tree.right);
			}
			if(tree.left != null) {
				stack.push(tree.left);
			}
		}
		System.out.println("");
		
	}
	
	/**
	 * 中序遍历，先把左边节点全部压入栈，依次弹出,弹出打印，处理右树，同理右树有左节点全部压入。
	 * @param tree
	 */
	public static void printTreeIn1(Tree root) {
		System.out.print("in-order : ");
		if(root == null) {
			return;
		}
		Stack<Tree> stack = new Stack<>();
		while(!stack.isEmpty() || root != null) {
			if(root != null) {
				stack.push(root);
				root = root.left;
			}else {
				root = stack.pop();
				System.out.print(root.value+" ");
				root = root.right;
			}
		}
		System.out.println();
		
	}
	/**
	 * 后续遍历
	 * @param tree
	 */
	public static void pos1(Tree tree) {
		System.out.print("pos-order-1 : ");
		if(tree == null) {
			return;
		}
		Stack<Tree> s1 = new Stack<>();
		Stack<Tree> s2 = new Stack<>();
		s1.add(tree);
		while(!s1.isEmpty()) {
			tree = s1.pop();
			s2.push(tree);
			if(tree.left != null) {
				s1.push(tree.left);
			}
			if(tree.right != null) {
				s1.push(tree.right);
			}
		}
		while(!s2.isEmpty()) {
			System.out.print(s2.pop().value+" ");
		}
		System.out.println();
		
	}
	/**
	 * 如果节点有左树，且节点的左树没有处理过，即没被h指向，先入栈
	 * 如果没有左孩子，将栈顶节点的右树入栈，这一步要求当前节点的右树没有被处理过，即没有被h指向
	 * 以上逻辑都没有命中则没有孩子，弹出并打印
	 * 
	 * ps: cur节点指向当前栈顶节点，h节点记录上一个弹出的节点
	 * 
	 * @param h 二叉树
	 */
	public static void pos2(Tree h) {
		if(null == h) {
			return;
		}
		Stack<Tree> s = new Stack<>();
		s.push(h);
		Tree cur = null;
		while(!s.isEmpty()) {
			cur = s.peek();
			if(cur.left != null && cur.left != h && cur.right != h) {
				s.push(cur.left);
			//已经处理右孩子了，表示左孩子已经处理过了。
			}else if(cur.right != null && cur.right != h) {
				s.push(cur.right);
			}else {
				System.out.print(s.pop().value + " ");
				h = cur;
			}
		}
		System.out.println();
		
	}
	public void pos2_2(Tree h) {
		if(h == null) {
			return;
		}
		Stack<Tree> s = new Stack<Tree>();
		s.push(h);
		Tree cur = null;
		while(!s.isEmpty()) {
			cur = s.peek();
			if(cur.left != null && cur.left != h && cur.right != h) {
				s.push(cur.left);
			}else if(cur.right != null && cur.right != h){
				s.push(cur.right);
			}else {
				System.out.print(s.pop().value + " ");
				h = cur;
			}
		}
	}
	
	public static void main(String[] args) {
		Tree head = new Tree(1);
		head.left = new Tree(2);
		head.right = new Tree(3);
		head.left.left = new Tree(4);
		head.left.right = new Tree(5);
		head.right.left = new Tree(6);
		head.right.right = new Tree(7);
		//先
		//printTreePre1(head);
		System.out.println("========");
		//中
		//printTreeIn1(head);
		System.out.println("========");
		//后
		//pos1(head);
		System.out.println("========");
		//后
		pos2(head);
		System.out.println("========");
	}
}
