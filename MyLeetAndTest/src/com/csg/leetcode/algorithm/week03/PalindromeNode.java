package com.csg.leetcode.algorithm.week03;

import java.util.Stack;


/**
 * 给定单链表是否回文链表
 * @author zhangxu
 *
 */
public class PalindromeNode {
	/**
	 * 链表所有节点压入栈中，依次取出比较
	 * @param node
	 * @return
	 */
	public static boolean palindrome1(Node node) {
		Stack<Node> stack = new Stack<Node>();
		Node cur = node;
		while(null != cur) {
			stack.push(cur);
			cur = cur.next;
		}
		while(null != node) {
			if(node.value != stack.pop().value) {
				return false;
			}
			node = node.next;
		}
		return true;
	}
	/**
	 * 将中点后半部分压入栈比较
	 * @param node
	 * @return
	 */
	public static boolean palindrome2(Node node) {
		if(node == null || node.next == null) {
			return false;
		}
		//奇数个找中点下一个，偶数个找上中点下一个
		Node fast = node;
		Node slow = node.next;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		Stack<Node> stack = new Stack<>();
		while(slow != null) {
			stack.push(slow);
			slow = slow.next;
		}
		while(!stack.isEmpty()) {
			if(node.value != stack.pop().value) {
				return false;
			}
			node = node.next;
		}
		return true;
	}
	/**
	 * 反转链表，前后两部分做比较，比较完后将后半部分恢复
	 * @param node
	 * @return
	 */
	public static boolean palindrome3(Node node) {
		if(node == null || node.next == null) {
			return true;
		}
		//慢指针
		Node n1 = node;
		//快指针
		Node n2 = node;
		while(n2.next != null && n2.next.next != null) {
			n2 = n2.next.next;
			n1 = n1.next;
		}
		//右半部分第一个节点
		n2 = n1.next;
		//并让中点的next指向null
		n1.next = null;
		//中间变量
		Node n3 = null;
		//交换node之间的指针,交换完成后n1成为新的头节点
		// ex: 1->2->3->2->1  ==> 1->2->3<-2<-1;
		while(n2 != null) {
			n3 = n2.next;	//记录n2的next节点
			n2.next = n1;   //交换
			n1 = n2;        //n1往后走
			n2 = n3;        //n2往后走
		}
		
		/*=======================*/
		
		//n1为后半部分反转后的新的头
		n3 = n1;
		n2 = node;
		boolean res = true;
		//比较
		while(n1 != null && n2 != null) {
			if(n1.value != n2.value) {
				res = false;
				break;
			}
			n1 = n1.next;
			n2 = n2.next;
		}
		//后半部分再次反转。
		n1 = n3.next;
		n3.next = null;
		while(n1 != null) {
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
		System.out.print(palindrome1(head) + " | ");
		System.out.print(palindrome2(head) + " | ");
		System.out.println(palindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(palindrome1(head) + " | ");
		System.out.print(palindrome2(head) + " | ");
		System.out.println(palindrome3(head) + " | ");
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
