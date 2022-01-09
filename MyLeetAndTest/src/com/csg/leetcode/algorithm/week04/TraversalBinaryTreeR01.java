package com.csg.leetcode.algorithm.week04;

import java.util.Stack;

public class TraversalBinaryTreeR01 {
	public static void pre(Tree head) {
		if(head == null) {
			return;
		}
		System.out.println("pre-order : ");
		Stack<Tree> stack = new Stack<Tree>();
		Tree cur = null;
		stack.push(head);
		while(!stack.isEmpty()) {
			cur = stack.pop();
			System.out.print(cur.value + " ");
			if(cur.right != null) {
				stack.push(cur.right);
			}
			if(cur.left != null) {
				stack.push(cur.left);
			}
		}
		System.out.println();
	}
	
	public static void in(Tree head) {
		if(head == null) {
			return;
		}
		System.out.println("in-order : ");
		Stack<Tree> stack = new Stack<>();
		while(!stack.isEmpty() || head != null) {
			if(head != null) {
				stack.push(head);
				head = head.left;
			}else {
				head = stack.pop();
				System.out.print(head.value + " ");
				head = head.right;
			}
		}
		System.out.println();
	}
	
	public static void pos1(Tree head) {
		if(head == null) {
			return;
		}
		Stack<Tree> stack1 = new Stack<Tree>();
		Stack<Tree> stack2 = new Stack<Tree>();
		stack1.push(head);
		while(!stack1.isEmpty()) {
			head = stack1.pop();
			stack2.push(head);
			if(head.left != null) {
				stack1.push(head.left);
			}
			if(head.right != null) {
				stack1.push(head.right);
			}
		}
		while(!stack2.isEmpty()) {
			System.out.print(stack2.pop().value + " ");
		}
		System.out.println();
	}
	
	public static void pos2(Tree h) {
		if(h == null) {
			return;
		}
		Tree c  = null;
		Stack<Tree> stack = new Stack<>();
		stack.add(h);
		while(!stack.isEmpty()) {
			c = stack.peek();
			if(c.left != null && c.left != h && c.right != h) {
				stack.push(c.left);
			}else if (c.right != null && c.right != h) {
				stack.push(c.right);
			}else {
				System.out.print(stack.pop().value + " ");
				h = c;
			}
		}
		System.err.println();
		
	}
	
	
	public static void pos2_2(Tree h) {
		if(h == null) {
			return;
		}
		Tree c = null;
		Stack<Tree> stack = new Stack<Tree>();
		stack.push(h);
		while(!stack.isEmpty()) {
			c = stack.peek();
			if(c.left != null && c.left != h && c.right != h) {
				stack.push(c.left);
			}else if(c.right != null && c.right != h) {
				stack.push(c.right);
			}else {
				System.out.println(stack.pop().value + " ");
				h = c;
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
		pre(head);
		System.out.println("========");
		//中
		in(head);
		System.out.println("========");
		//后
		pos1(head);
		System.out.println("========");
		//后
		pos2(head);
		System.out.println("========");
	}
}
