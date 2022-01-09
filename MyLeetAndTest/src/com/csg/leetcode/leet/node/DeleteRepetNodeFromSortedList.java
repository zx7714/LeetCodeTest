package com.csg.leetcode.leet.node;

/**
 * 删除有序链表中的重复节点
 * 
 * @author zhangxu
 *
 */
public class DeleteRepetNodeFromSortedList {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode deleteDuplicates(ListNode head) {
		ListNode cur = head;
		while (cur != null && cur.next != null) {
			if (cur.val == cur.next.val) {
				cur.next = cur.next.next;
				continue;
			}
			cur = cur.next;
		}
		return head;
	}
}
