package com.csg.leetcode.algorithm.week03;

public class ReverseSingleNode {
	public static Node reverse(Node node) {
		if(node == null || node.next==null) {
			return node;
		}
		Node next = node.next;
		Node help = null;
		node.next = null;
		while(next != null) {
			help = next.next;
			next.next = node;
			node = next;
			next = help;
		}
		return node;
	}
	
	
	public static Node reverse2(Node head) {
		if(head ==null || head.next == null) {
			return head;
		}
		Node next = head.next;
		head.next = null;
		Node help = null;
		while(next != null) {
			help = next.next;
			next.next = head;
			head = next;
			next = help;
		}
		return head;
	}
	
	public static void main(String[] args) {
		Node head = null;
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(4);
		head.next.next.next = new Node(5);
		printLinkedList(head);
		Node help = reverse2(head);
		printLinkedList(help);
	}
	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}
}
