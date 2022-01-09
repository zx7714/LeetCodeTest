package com.csg.leetcode.leet.node;

/**
 * 两两交换列表中节点的位置
 * 
 * @author zhangxu
 *
 */
public class SwapNodeInPairs {
	public static class ListNode {
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

	public static ListNode swapPairs(ListNode head) {
		if(head== null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		//head为交换后的节点，继续交换下一组节点
		head.next = swapPairs(newHead.next);
		newHead.next = head;
		return newHead;
	}
	
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		
		node = swapPairs(node);
		while(node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}
	
	public static ListNode swapPairs2(ListNode head) {
		ListNode cur = new ListNode();
		cur.next = head;
		ListNode pre = cur;
		ListNode next = null;
		while(pre != null && pre.next != null) {
			next = pre.next;
			pre.next = pre.next.next;
			
			next.next = pre.next.next;
			pre.next.next = next;
			pre = next;
		}
		return cur.next;
	}
	
	
	public static ListNode swapPairs3(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode newHead = head.next;
		head.next = swapPairs(newHead.next);
		newHead.next = head;
		return head;
	}
	

}
