package com.csg.leetcode.algorithm.week05;

public class IsFull {
	public static boolean isFull1(Node head) {
		if(null == head) {
			return true;
		}
		int height = h(head);
		int nodes = n(head);
		return nodes == (1 << height) - 1;
	}

	private static int h(Node head) {
		if(null == head) {
			return 0;
		}
		return Math.max(h(head.left),h(head.right)) + 1;
	}
	
	private static int n(Node head) {
		if(null == head) {
			return 0;
		}
		return n(head.left) + n(head.right) + 1;
	}

	
	public static class Info{
		int heigh;
		int nodes;
		public Info(int heigh,int nodes) {
			this.heigh = heigh;
			this.nodes = nodes;
		}
	}
	public static boolean isFull2(Node head) {
		if(null == head) {
			return true;
		}
		Info info = process(head);
		return info.nodes == (1 << info.heigh) -1;
	}

	private static Info process(Node head) {
		if(head == null) {
			return new Info(0, 0);
		}
		
		Info left = process(head.left);
		Info right = process(head.right);
		return new Info(
				Math.max(left.heigh,right.heigh) +1,
				left.nodes + right.nodes + 1
				);
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
			if (isFull1(head) != isFull2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
