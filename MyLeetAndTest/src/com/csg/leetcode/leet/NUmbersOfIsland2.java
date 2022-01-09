package com.csg.leetcode.leet;

import java.util.LinkedList;
import java.util.Queue;

public class NUmbersOfIsland2 {
	public static class UnionSet{
		int count;
		int[] parent;
		public UnionSet(int n) {
			count = n;
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}
		
		public int findFather(int n) {
			Queue<Integer> q = new LinkedList<>();
			while(n != parent[n]) {
				//一次找两个。
				n = parent[n];
				q.add(n);
			}
			//合并父节点
			while(!q.isEmpty()) {
				parent[q.poll()] = n;
			}
			return n;
		}
		
		public void union(int x ,int y) {
			int xFather = findFather(x);
			int yFather = findFather(y);
			if(xFather == yFather) {
				return;
			} 
			parent[xFather] = yFather;
			count --;
		}
		
		
		
	}
	
	
	public static int rows;
	public static int cols; 
	public static int numIslands(char[][] grids) {
		
		rows = grids.length;
		if(0 == rows) {
			return 0;
		}
		cols = grids[0].length;
		
		//初始并查集
		UnionSet us = new UnionSet(rows * cols);
		int spaces = 0;
		int[][] directions = {{1,0},{0,1}}; 
		for(int i = 0;i < rows;i++) {
			for(int j = 0;j<cols;j++) {
				if(grids[i][j] == '0') {
					spaces ++;
				}else {
					for(int[] dir : directions) {
						int xNew = dir[0] + i;
						int yNew = dir[1] + j;
						if(xNew < rows && yNew < cols && grids[xNew][yNew] == '1') {
							us.union(getIndex(i,j),getIndex(xNew,yNew));
						}
					}
				}
			}
		}
		
		return us.count - spaces;
	}
	private static int getIndex(int i, int j) {
		return i * cols + j;
	}
	public static void main(String[] args) {
		char a1[] = {'1','1','0','0','0'};
		char a2[] = {'1','1','0','0','0'};
		char a3[] = {'0','0','1','0','0'};
		char a4[] = {'0','0','0','1','1'};
		char aa1[][] = {a1,a2,a3,a4};
		System.out.println(numIslands(aa1));
		
	}
}
