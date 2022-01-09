package com.csg.leetcode.leet.node;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。 传入函数的唯一参数为 <要被删除的节点 >。
 * 
 * @author zhangxu
 *
 */
public class DeleteNodeSelfInNode {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	public void deleteNode(ListNode node) {
		if(node == null ||
				node.next == null) {
			return;
		}
		int val = node.next.val;
		node .val = val;
		node.next = node.next.next;
	}
	
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		
	}
}
