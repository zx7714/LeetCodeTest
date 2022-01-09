package com.csg.leetcode.leet;

/**岛数量
 * 
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 
 * 此外，你可以假设该网格的四条边均被水包围。
 * 
 * 示例 1：
 * 
 * 输入：grid = [ ["1","1","1","1","0"], ["1","1","0","1","0"],
 * ["1","1","0","0","0"], ["0","0","0","0","0"] ] 输出：1 示例 2：
 * 
 * 输入：grid = [ ["1","1","0","0","0"], ["1","1","0","0","0"],
 * ["0","0","1","0","0"], ["0","0","0","1","1"] ] 输出：3
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @param grid
 * @return
 */
public class N_200_NumbersOfIsLand {

	private static int rows;
	private static int cols;

	public static int numIslands(char[][] grid) {
		rows = grid.length;
		if (rows == 0) {
			return 0;
		}
		cols = grid[0].length;

		// 水的数量
		int spaces = 0;
		//二维数组转为以为数组
		UnionFind unionFind = new UnionFind(rows * cols);
		// 只往前看和往下看
		int[][] directions = { { 1, 0 }, { 0, 1 } };
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				// 水
				if (grid[i][j] == '0') {
					spaces++;
				} else {
					// 此时 grid[i][j] == '1'，表示陆地
					for (int[] direction : directions) {
						int newX = i + direction[0];
						int newY = j + direction[1];
						// 先判断坐标合法，再检查右边一格和下边一格是否是陆地
						if (newX < rows && newY < cols && grid[newX][newY] == '1') {
							unionFind.union(getIndex(i, j), getIndex(newX, newY));
						}
					}
				}
			}
		}
		return unionFind.getCount() - spaces;
	}

	private static int getIndex(int i, int j) {
		return i * cols + j;
	}

 	private static class UnionFind {
		/**
		 * 连通分量的个数
		 */
		private int count;
		private int[] parent;

		public int getCount() {
			return count;
		}

		public UnionFind(int n) {
			this.count = n;
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		private int find(int x) {
			while (x != parent[x]) {
				parent[x] = parent[parent[x]];
				x = parent[x];
			}
			return x;
		}

		public void union(int x, int y) {
			int xRoot = find(x);
			int yRoot = find(y);
			if (xRoot == yRoot) {
				return;
			}

			parent[xRoot] = yRoot;
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
