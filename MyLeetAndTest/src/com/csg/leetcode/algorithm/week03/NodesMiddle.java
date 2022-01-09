package com.csg.leetcode.algorithm.week03;

public class NodesMiddle {
	//奇数个取中点，偶数个取上中点
	public Node midOrUpMiddleNode(Node node) {
		if(node ==null || node.next == null 
				|| node.next.next == null) {
			return node;
		}
		Node fast = node.next.next;
		Node slow = node.next;
		while(fast.next!=null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	//奇数个取中点，偶数取个下中点
	public Node midOrDownMiddleNode(Node node) {
		if(node == null || node.next == null) {
			return node;
		}
		Node fast = node.next;
		Node slow = node.next;
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	//奇数取中点上一个，偶数个取上中点上一个
	public Node midOrUpMiddlePreNode(Node node) {
		if(node == null || node.next == null 
				|| node.next.next == null) {
			return node;
		}
		Node slow = node;
		Node fast = node.next.next;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	//奇数个取中点上一个，偶数取下中点上一个
	public Node midOrDownMiddlePreNode(Node node) {
		if(node == null || node.next ==null) {
			return null;
		}
		if(node.next.next == null) {
			return node;
		}
		Node fast = node.next;
		Node slow = node;
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		
		return slow;
	}
}
