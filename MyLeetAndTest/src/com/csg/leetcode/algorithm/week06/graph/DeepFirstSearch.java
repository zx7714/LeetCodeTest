package com.csg.leetcode.algorithm.week06.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DeepFirstSearch {
	public void dfs(Node head) {
		Stack<Node> stack = new Stack<>();
		stack.add(head);
		Set<Node> set = new HashSet<>();
		set.add(head);
		System.out.print(head.value);
		while(!stack.isEmpty()) {
			Node cur = stack.pop();
			List<Node> nodes = cur.nodes;
			for (Node node : nodes) {
				if(set.contains(node)) {
					continue;
				}
				stack.push(cur);
				stack.push(node);
				set.add(node);
				System.out.print(" "+node.value);
				break;
			}
		}
	}
}
