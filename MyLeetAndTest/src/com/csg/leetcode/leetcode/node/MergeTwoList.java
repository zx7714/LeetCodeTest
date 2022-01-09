package com.csg.leetcode.leetcode.node;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MergeTwoList {
	public static ListNode mergeTwoLists(ListNode l1,ListNode l2){
		if(l1==null) {
			return l2;
		}
		if(l2==null) {
			return l1;
		}
		//开头第一个数最小的作为头结点
		ListNode newHead = l1.val < l2.val ? l1 : l2;
		ListNode cur1 = newHead;
		ListNode cur2 = newHead == l2 ? l1 : l2; 
		//记录cur1的前一个节点
		ListNode pre = cur1;
		cur1 = cur1.next;
		ListNode next=null;
		while(cur1 != null && cur2 != null) {
			if(cur1.val < cur2.val) {
				pre = cur1;
				cur1 = cur1.next;
			} else {
				//next记录cur2的next;
				next = cur2.next;
				pre.next = cur2;
				pre.next.next = cur1;
				cur2 = next;
				pre = pre.next;
			}
		}
		if(cur2 != null) {
			pre.next = cur2;
		}
		return newHead;
	}
	
	public static ListNode recursionMergeTwoLists(ListNode l1,ListNode l2){
		if(l1 == null) {
			return l2;
		}
		if(l2 == null) {
			return l1;
		}
		
		if(l1.val < l2.val) {
			l1.next = recursionMergeTwoLists(l1.next,l2);
			return l1;
		}else {
			l2.next = recursionMergeTwoLists(l1, l2.next);
			return l2;
		}
	}
	public static ListNode mergetTwoLists(ListNode l1 ,ListNode l2){
		if(l1 == null) {
			return l2;
		}
		if(l2 == null) {
			return l1;
		}
		Queue<ListNode> queue = new LinkedBlockingQueue<ListNode>();
		while(l1 != null && l2 != null) {
			if(l1.val <= l2.val) {
				queue.add(l1);
				l1 = l1.next;
			}else {
				queue.add(l2);
				l2 = l2.next;
			}
		}
		while(l1 != null) {
			queue.add(l1);
			l1 = l1.next;
		}
		while(l2 != null) {
			queue.add(l2);
			l2 = l2.next;
		}
		l2 = queue.poll();
		l1 = l2;
		while (!queue.isEmpty()) {
			l2.next = queue.poll();
			l2 = l2.next;
		}
		return l1;
	}
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(2);
		node1.next = new ListNode(3);
		node1.next.next = new ListNode(6);
		node1.next.next.next = new ListNode(9);
		
		ListNode node2 = new ListNode(2);
		node2.next = new ListNode(7);
		node2.next.next = new ListNode(8);
		node2.next.next.next = new ListNode(10);
		
		ListNode newNode = mergetTwoLists(node1,node2);
		
		while(newNode != null) {
			System.out.println(newNode.val);
			newNode = newNode.next;
		}
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

