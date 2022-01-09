package com.csg.leetcode.leet;

public class NumbersOfIsLand1 {
	//行数
	private static int rows;
	//列数
	private static int cols;
	
	public static int numIslands(char[][] aa1) {
		rows = aa1.length;
		if(0 == rows) {
			return 0;
		}
		cols = aa1[0].length;
		
		//所有空地数
		int space = 0;
		//初始化并查集
		UnionSet us = new UnionSet(cols * rows);
		
		//只向下查找和向右查找，其他不关心
		int looks[][] = {{1,0},{0,1}};
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				//如果是水
				if(aa1[i][j] == '0') {
					space ++;
				//如果不是水
				}else {
					for(int[] look : looks) {
						int xNew = i + look[0];
						int yNew = j + look[1];
						if(xNew < rows && yNew < cols && aa1[xNew][yNew] == '1') {
							us.union(getIndex(i, j), getIndex(xNew, yNew));
						}						
					}
				}
			}
		}
		
		return us.count-space;
	}
	
	private static int getIndex(int x,int y) {
		return x * cols + y;
	}
	
	public static class UnionSet{
		int count;
		int parent[];
		public UnionSet(int size) {
			count = size;
			parent = new int[size];
			//初始化
			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}
		
		public int findParent(int num) {
			while(num != parent[num]) {
				parent[num] = parent[parent[num]];
				num = parent[num]; 
			}
			return num;
		}
		
		public void union(int num1,int num2) {
			int parent1 = findParent(num1);
			int parent2 = findParent(num2);
			if(parent1 == parent2) {
				return;
			}
			parent[parent1] = parent2;
			count--;
		}
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
