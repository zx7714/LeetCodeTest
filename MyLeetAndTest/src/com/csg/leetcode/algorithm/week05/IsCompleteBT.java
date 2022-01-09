package com.csg.leetcode.algorithm.week05;

import java.util.LinkedList;
import java.util.Queue;

public class IsCompleteBT {
	
	public static boolean isCBT1(Node head) {
		if(head == null) {
			return true;
		}
		Queue<Node> q = new LinkedList<>();
		q.add(head);
		boolean leaf = false;
		Node l = null;
		Node r = null;
		while(!q.isEmpty()) {
			head = q.poll();
			l = head.left;
			r = head.right;
			//宽度优先遍历（遍历当前层数），如果出现过叶节点又发现了左右孩子不为空的节点，一定不是完全二叉树。
			//有右树却又没有左树，也一定不是完全二叉树
			
			if(
				(leaf && (l != null || r != null) || 
				(l == null && r != null))
			) {
				return false;
			}
			
			if(l != null) {
				q.add(l);
			}
			if(r != null) {
				q.add(r);
			}
			
			if(l == null || r == null) {
				leaf = true;
			}
		}
		
		
		return true;
	}
	
	public static class Info{
		boolean isFull;
		boolean isComplete;
		int heigh;
		public Info(boolean isFull,boolean isComplete,int heigh) {
			this.isFull = isFull;
			this.isComplete = isComplete;
			this.heigh = heigh;
		}
	}
	
	public static boolean isCBT2(Node head) {
		if(head == null) {
			return true;
		}
		
		Info info = process(head);
		return info.isComplete;
	}

	private static Info process(Node head) {
		if(null == head) {
			return new Info(true, true, 0);
		}
		
		Info left = process(head.left);
		Info right = process(head.right);
		
		
		int heigh = Math.max(left.heigh, right.heigh) +1;
		boolean isFull = left.isFull && right.isFull && left.heigh == right.heigh;
		
		boolean isComplete = false;
		if(isFull) {
			isComplete = true;
		}else {
			if(right.isFull && left.isComplete
					&& left.heigh - right.heigh == 1) {
				isComplete = true;
			}
			
			if(right.isFull && left.isFull
					&& left.heigh - right.heigh == 1) {
				isComplete = true;
			}
			
			if(left.isFull && right.isComplete
					&& left.heigh == right.heigh) {
				isComplete = true;
			}
		}
		
		return new Info(isFull,isComplete,heigh);
	}
	
	
	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			boolean cbt1= isCBT1(head);
			boolean cbt2 = isCBT2(head);
			if (cbt1 != cbt2) {
				System.out.println(cbt1);
				System.out.println(cbt2);
				cbt1= isCBT1(head);
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
