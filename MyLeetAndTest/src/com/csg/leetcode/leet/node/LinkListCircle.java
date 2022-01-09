package com.csg.leetcode.leet.node;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是
 * -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * 
 * 说明：不允许修改给定的链表。
 * 
 * 进阶：
 * 
 * 你是否可以使用 O(1) 空间解决此题？
 * 
 * @author zhangxu
 *
 */
public class LinkListCircle {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode detectCycle(ListNode head) {
		if (null == head || null == head.next || null == head.next.next) {
			return head;
		}
		ListNode n1 = head.next.next;
		ListNode n2 = head.next;
		while (n1 != n2) {
			n1 = n1.next.next;
			n2 = n2.next;
		}
		//入环节点
		n1 = head;
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		n1 = head;
		while (n1 != n2) {
			n1 = n1.next;
		}

		return n1;
	}
	
	public boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) {
			return false;
		}
		ListNode fast = head.next.next;
		ListNode slow = head.next;
		while(fast != slow ) {
			if(fast == null || fast.next == null || fast.next.next == null) {
				return false;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		return true;
		
    }
}
