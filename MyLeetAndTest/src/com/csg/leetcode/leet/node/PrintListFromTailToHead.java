package com.csg.leetcode.leet.node;

/**
 * 从尾到头遍历链表
 * 
 * @author zhangxu
 */
public class PrintListFromTailToHead {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public int[] reversePrint(ListNode head) {
		int count = 0;
		ListNode next = null;
		ListNode cur = null;
		while(head != null) {
			next = head.next;
			head.next = cur;
			cur = head;
			head = next;
			count ++;
		}
		next = null;
		head = null;
		int[] ans = new int[count];
		count = 0;
		while(cur != null) {
			ans[count++] = cur.val;
			next = cur.next;
			cur.next = head;
			head = cur;
			cur = next;
		}
		
		
		return ans;
		
	}
}
