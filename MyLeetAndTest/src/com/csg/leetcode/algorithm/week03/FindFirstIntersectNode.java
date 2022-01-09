package com.csg.leetcode.algorithm.week03;

/**
 * 一个单向链表，可能有环可能五环，找到他的入环节点如果五环返回null
 * @author zhangxu
 *
 */
public class FindFirstIntersectNode {
	
	public static Node findIntersectNode(Node head1,Node head2) {
		if(null == head1 || null == head2) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if(null == loop1 && null == loop2) {
			return noLoopIntersect(head1,head2,null);
		}
		
		if(loop1 != null && loop2 != null) {
			return loopIntersect(head1,loop1,head2,loop2);
		}
		
		return null;
		
	}
	
	//有环相交
	private static Node loopIntersect(Node head1, Node loop1, Node head2, Node loop2) {
		
		Node cur1 = head1;
		Node cur2 = head2;
		//在入环之前已经相交了
		if(loop1 == loop2) {
			int n = 0;
			while(cur1.next != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while(cur2.next != loop1) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while(n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while(cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}else {
			cur1 = loop1.next;
			while(cur1 != loop1) {
				if(cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
		
	}


	private static Node noLoopIntersect(Node head1, Node head2,Node end) {
		
		Node n1 = head1;
		Node n2 = head2;
		int n = 0;
		while(n1.next != end) {
			n++;
			n1 = n1.next;
		}
		while(n2.next != end) {
			n--;
			n2 = n2.next;
		}
		if(n1 != n2) {
			return null;
		}
		n1 = n > 0 ? head1 : head2;
		n2 = n1==head1 ? head2 : head1;
		
		n = Math.abs(n);
		
		while(n != 0) {
			n--;
			n1 = n1.next;
		}
		while(n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		
		return n1;
	}


	//无环相交。
	public static Node getLoopNode(Node node) {
		if(node == null || node.next == null || node.next.next ==null) {
			return null;
		}
		Node slow = node.next;
		Node fast = node.next.next;
		while(fast != slow) {
			if(fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = node;
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
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
