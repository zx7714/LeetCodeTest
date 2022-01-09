package com.csg.leetcode.algorithm.week03;
/**
 * 找链表的相交节点
 * @author zhangxu
 *
 */
public class FindIntersectNode {
	
	
	public static Node findIntersectNode(Node head1,Node head2) {
		Node loop1 = findLoopNode(head1);
		Node loop2 = findLoopNode(head2);
		//无环
		if(loop1 == null && loop2 == null) {
			return findNoLoopIntersectNode(head1,null,head2,null);
		}
		
		if(loop1 != null && loop2 != null) {
			return findBothLoopIntersectNode(head1,loop1,head2,loop2);
		}
		return null;
	}

	private static Node findBothLoopIntersectNode(Node head1, Node loop1, Node head2, Node loop2) {
		if(loop1 == loop2) {
			return findNoLoopIntersectNode(head1, loop1, head2, loop2);
		}else {
			Node cur1 = loop1.next;
			while(cur1 != loop1) {
				if(cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
		}
		return null;
	}

	private static Node findNoLoopIntersectNode(Node head1,Node loop1,Node head2,Node loop2) {
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while(cur1 != loop1) {
			n++;
			cur1 = cur1.next;
		}
		while(cur2 != loop2) {
			n--;
			cur2 = cur2.next;
		}
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1 == head2 ? head1 : head2;
		n = Math.abs(n);
		while(n != 0) {
			n--;
			cur1 = cur1.next;
		}
		
		while(cur1 != cur2 ) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	private static Node findLoopNode(Node head) {
		if(head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node fast = head.next.next;
		Node slow = head.next;
		while(fast != slow) {
			if(fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
	
	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(findIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(findIntersectNode(head1, head2).value);

	}
}
