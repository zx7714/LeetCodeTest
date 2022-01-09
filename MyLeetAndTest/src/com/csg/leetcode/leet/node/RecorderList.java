package com.csg.leetcode.leet.node;

import com.csg.leetcode.leet.node.SwapNodeInPairs.ListNode;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 
 * @author zhangxu
 *
 */
public class RecorderList {

	public static void reorderList(ListNode head) { 
	if(head == null || head.next == null || head.next.next == null){
		return;
	}
	ListNode n1 = head.next;
	ListNode n2 = head.next;
	// 找到链表的中点或上中点n2
	while (n1 != null && n1.next != null && n1.next.next != null) {
		n1 = n1.next.next;
		n2 = n2.next;
	}

	// 记录以尾为头的链表的头节点。
	n1 = null;

	// next
	ListNode n3 = null;
	while (n2 != null) {
		n3 = n2.next;
		n2.next = n1;
		n1 = n2;
		n2 = n3;
	}
	// 从头开始
	n2 = head;
	ListNode n4 = null;
	while (n1 != null && n1.next != null) {
		n3 = n2.next;
		n4 = n1.next;
		n2.next = n1;
		n1.next = n3;
		n2 = n3;
		n1 = n4;
	}}
	
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		
		reorderList(node);
		System.out.println("-----");
		
	}
}
