package com.csg.leetcode.leet.node;

import com.csg.leetcode.leet.node.SwapNodeInPairs.ListNode;

public class DeleteNodeEqualsTarget {
	public ListNode removeElements(ListNode head, int val) {
		while (head != null) {
			if (head.val != val) {
				break;
			}
			head = head.next;
		}
		if (head == null) {
			return head;
		}
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			if (cur.val != val) {
				pre = cur;
				cur = cur.next;
			} else {
				pre.next = cur.next;
				cur = cur.next;
			}
		}
		return head;

	}
}
