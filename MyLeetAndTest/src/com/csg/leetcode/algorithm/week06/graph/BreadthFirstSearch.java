package com.csg.leetcode.algorithm.week06.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 宽度优先遍历
 * @author zhangxu
 *
 */
public class BreadthFirstSearch {
	public void bfs(Node head) {
		Queue<Node> q = new LinkedList<>();
		q.add(head);
		//避免有向图循环引用
		Set<Node> set = new HashSet<>();
		set.add(head);
		while(!q.isEmpty()) {
			Node curr = q.poll();
			System.out.print(curr.value + " ");
			List<Node> nexts = curr.nodes;
			for(Node node : nexts) {
				if(set.contains(node)) {
					continue;
				}
				q.add(node);
				set.add(node);
			}
			
		}
	}
}
