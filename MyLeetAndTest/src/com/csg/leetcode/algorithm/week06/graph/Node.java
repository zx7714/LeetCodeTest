package com.csg.leetcode.algorithm.week06.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
	int value;
	//入度
	int in;
	//出度
	int out;
	//节点
	List<Node> nodes;
	//边
	List<Edge> edges;
	
	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
