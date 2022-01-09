package com.csg.leetcode.algorithm.week03;

public class PlindromeNode3 {
	public static boolean plindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		Node n1 = head;
		Node n2 = head;
		// 找中点
		while (n2.next != null && n2.next.next != null) {
			n2 = n2.next.next;
			n1 = n1.next;
		}

		n2 = n1.next;
		n1.next = null;
		// 辅助变量
		Node n3 = null;
		// 反转后半部分
		while (n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}

		// 比较
		n3 = n1;
		n2 = head;
		boolean res = true;
		while (n1 != null && n2 != null) {
			if (n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next;
			n2 = n2.next;
		}

		// 再反转回来
		n2 = n3.next;
		n3.next = null;
		while (n2 != null) {
			n1 = n2.next;
			n2.next = n3;
			n3 = n2;
			n2 = n1;
		}
		return res;
	}

	public static void main(String[] args) {
		Node head = null;
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
//		System.out.print(palindrome1(head) + " | ");
//		System.out.print(palindrome2(head) + " | ");
		System.out.println(plindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
//		System.out.print(palindrome1(head) + " | ");
//		System.out.print(palindrome2(head) + " | ");
		System.out.println(plindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");
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
