package com.csg.leetcode.algorithm.week05;

import java.util.ArrayList;
import java.util.List;
/**
 * 给定一棵二叉树的头节点head，返回这棵二叉树中最大的搜索子树的头节点  
 * @author zhangxu
 *
 */
public class MaxSubBSTHead {
	
	public static Node maxBSTHead1(Node head) {
		if(head == null) {
			return null;
		}
		if(getBSTSize(head) != 0) {
			return head;
		}
		Node leftAns = maxBSTHead1(head.left);
		Node rightAns = maxBSTHead1(head.right);
		return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
	}

	private static int getBSTSize(Node head) {
		if(head == null) {
			return 0;
		}
		List<Node> nodeList = new ArrayList<Node>();
		in(head,nodeList);
		
		for(int i = 1;i < nodeList.size();i++) {
			if(nodeList.get(i).value <= nodeList.get(i-1).value) {
				return 0;
			}
		}

		return nodeList.size();
	}

	private static void in(Node head, List<Node> nodeList) {
		if(null == head) {
			return;
		}
		in(head.left, nodeList);
		nodeList.add(head);
		in(head.right, nodeList);
	}

	public static class Info{
		Node maxSubHead;
		int maxSubSize;
		int max;
		int min;
		public Info(Node head,int size,int max,int min) {
			this.maxSubHead = head;
			this.maxSubSize = size;
			this.max = max;
			this.min = min;
		}
	}
	
	public static Node maxBSTHead2(Node head) {
		if(head == null) {
			return head;
		}
		Info info = process(head);
		return info.maxSubHead;
	}

	private static Info process(Node head) {
		if(head == null) {
			return null;
		}
	
		Info left = process(head.left);
		Info right = process(head.right);
		
		int max = head.value;
		int min = head.value;
		Node maxSubBSTHead = null;
		int maxSubBSTSize = 0;
		if(left != null) {
			min = Math.min(left.min, min);
			max = Math.max(left.max, max);
			maxSubBSTHead = left.maxSubHead;
			maxSubBSTSize = left.maxSubSize;
		}
		
		if(right != null) {
			min = Math.min(right.min, min);
			max = Math.max(right.max, max);
			if(right.maxSubSize > maxSubBSTSize) {
				maxSubBSTHead = right.maxSubHead;
				maxSubBSTSize = right.maxSubSize;
			}
		}

		if(
			(left == null ? true : (left.maxSubHead == head.left 
										&& 
									head.value > left.max))
			&&
			(right == null ? true : (right.maxSubHead == head.right 
										&& 
									head.value < right.min))
			) {
			maxSubBSTHead = head;
			maxSubBSTSize  = (left == null ? 0 : left.maxSubSize)+
							 (right == null ? 0 : right.maxSubSize)+
							 1;
		}
		return new Info(maxSubBSTHead, maxSubBSTSize, max, min);
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
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node maxBSTHead1 = maxBSTHead1(head);
			Node maxBSTHead2 = maxBSTHead2(head);
			if ( maxBSTHead1 != maxBSTHead2) {
				System.out.println(maxBSTHead1.value);
				System.out.println(maxBSTHead2.value);
				System.out.println();
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
		
		Node node = new Node(71);
		node.right = new Node(78);
		node.right.left = new Node(90);
		node.right.right = new Node(1);
		node.right.right.right = new Node(2);
		
		Node maxBSTHead2 = maxBSTHead2(node);
		Node maxBSTHead1 = maxBSTHead1(node);
		System.out.println(maxBSTHead1.value);
		System.out.println(maxBSTHead2.value);
	}
}
