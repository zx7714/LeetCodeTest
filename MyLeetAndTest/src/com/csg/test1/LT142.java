package com.csg.test1;

public class LT142 {


    public ListNode detectCycle(ListNode head) {
        if (null == head || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode s = head.next;
        ListNode f = head.next.next;
        while (s != f) {
            if (f.next == null || f.next.next == null) {
                return null;
            }
            s = s.next;
            f = f.next;
        }
        f = head;
        while (s != f) {
            f = f.next;
            s = s.next;
        }
        return s;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
