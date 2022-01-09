package com.csg.leetcode.leet.node;

import com.csg.leetcode.leet.node.SwapNodeInPairs.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 
 * 说明: 1 ≤ m ≤ n ≤ 链表长度。
 * 
 * @author zhangxu
 *
 */
public class RerverseLikneII {
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = new ListNode();
		newHead.next = head;
		
		
		ListNode pre = null;
		ListNode cur = newHead;
		int index = m;
		while (index != 0) {
			pre = cur;
			cur = cur.next;
			index--;
		}
		// 交换从m到n的节点
		ListNode tail = null;
		//断开时的点。
		ListNode n1 = cur;
		// 认为与前面的节点，新的链表
		ListNode n2 = null;
		index = n - m + 1;

		while (index != 0) {
			tail = cur.next;
			cur.next = n2;
			n2 = cur;
			cur = tail;
			index--;
		}
		pre.next = n2;
		n1.next = tail;
		return newHead.next;
	}

	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);

		reverseBetween(node, 2, 4);

		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
		System.out.println("----");

	}
}
