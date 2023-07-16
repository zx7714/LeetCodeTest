package com.csg.leetcode.leet.graph;

import java.util.*;

public class N133 {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }
        if (node.neighbors.size() == 0) {
            return new Node(node.val);
        }

        Node copy = new Node(node.val);
        Map<Integer, Node> copyMap = new HashMap<>();
        copyMap.put(copy.val, copy);
        Set<Integer> done = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (done.contains(cur.val)) {
                continue;
            }
            for (Node next : cur.neighbors) {
                boolean exist = copyMap.get(next.val) != null;
                Node copyNext = exist ? copyMap.get(next.val) : new Node(next.val);
                Node parentNode = copyMap.get(cur.val);
                parentNode.neighbors.add(copyNext);
                if(!exist) {
                    copyMap.put(next.val, copyNext);
                }
                queue.add(next);
            }
            done.add(cur.val);
        }
        return copy;

    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node nodeCopy = cloneGraph(node1);
        System.out.println(1);

    }
}
