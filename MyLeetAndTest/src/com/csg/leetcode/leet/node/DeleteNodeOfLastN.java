package com.csg.leetcode.leet.node;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * @author zhangxu
 *
 */
public class DeleteNodeOfLastN {
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
	/**
	 * 考虑换头
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode nNode = head;
		// 5 4 3 2 1 
		while(n != 0) {
			nNode = nNode.next;
			n--;
		}
		ListNode cur = head;
		while(nNode != null &&
            nNode.next != null) {
			cur = cur.next;
			nNode = nNode.next;
		}

		//换头情况，链表长度与与输入的一样。
        if(cur == head && nNode == null){
            return cur.next;
        }

        if(cur.next.next ==null){
            cur.next = null;
        }else{
            cur.next = cur.next.next;
        }
		return head;
	}
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		
		System.out.println(removeNthFromEnd(node,1));
	}
	
}
