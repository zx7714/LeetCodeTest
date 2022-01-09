package com.csg.leetcode.algorithm.week03;

public class PalindromeNode2 {
	public static boolean palindrome(Node head) {
		Node n1 = head.next;
		Node n2 = head.next.next;
		while(n2.next != null && n2.next.next != null) {
			n1 = n1.next;
			n2 = n2.next.next;
		}
		n2 = n1.next;
		n1.next = null;
		Node n3 = null;
		while(n2 != null) {
			n3 = n2.next;
			n2.next = n1;
			n1 = n2;
			n2 = n3;
		}
		n3 = n1;
		n2 = head;
		boolean res = true;
		//核对
		while(n1 != null && n2 != null) {
			if(n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		
		//反转
		n1 = n3.next;
		n3.next = null;
		while(n1 != null){
			n2 = n1.next;
			n1.next = n3;
			n3 = n1;
			n1 = n2;
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
		System.out.println(palindrome(head) + " | ");
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
		System.out.println(palindrome(head) + " | ");
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
