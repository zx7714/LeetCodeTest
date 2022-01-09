package com.csg.leetcode.algorithm.week05;

import java.util.ArrayList;
import java.util.List;

public class MaxHappy {
	public static class Employee{
		public int happy;
		public List<Employee> nexts;
		public Employee(int happy) {
			this.happy = happy;
			nexts = new ArrayList<>();
		}
	}
	
	public static int maxhappy1(Employee boss) {
		if(boss == null) {
			return 0;
		}
		return process1(boss,false);
	}
	/**
	 * 1.cur表示当前来到的节点
	 * 2.up表示上级节点是否来
	 * 3.如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	 * 4.如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	 * 
	 * @param cur
	 * @param up
	 * @return
	 */
	private static int process1(Employee cur, boolean up) {
		if(up) {
			int ans = 0;
			for(Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		}else {
			int p1 = cur.happy;
			int p2 = 0;
			for(Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next,false);
			}
			return Math.max(p1, p2);
		}
	}

	public static class Info{
		// 去，和所有下级员工可以制造的最大快乐值
		int yes;
		// 不去，和所有下级员工可以制造的最大快乐值
		int no;
		public Info(int yes,int no) {
			this.yes = yes;
			this.no = no;
		}
	}
	
	/**
	 * 每个员工都可以选择去或不去；
	 * 当前员工去了，所有直接下级员工都不能去。
	 * @param boss
	 * @return
	 */
	public static int maxhappy2(Employee boss) {
		if(boss == null) {
			return 0;
		}
		
		Info info = process2(boss);
		return Math.max(info.yes, info.no);
	}
	private static Info process2(Employee cur) {
		if(cur.nexts == null) {
			return new Info(cur.happy, 0);
		}
		
		int p1 = cur.happy;
		int p2 = 0;
		for(Employee next : cur.nexts) {
			Info nInfo = process2(next);
			//当前员工去了，下级员工可以选择去或不去
			//此时统计当前员工去的情况下后面员工的最大快乐值。
			p2 += Math.max(nInfo.yes, nInfo.no);
			
			//当前员工去了，择下级员工只能不去。
			p1 += nInfo.no;
		}
		return new Info(p1, p2);
	}
	
	// for test
		public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
			if (Math.random() < 0.02) {
				return null;
			}
			Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
			genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
			return boss;
		}

		// for test
		public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
			if (level > maxLevel) {
				return;
			}
			int nextsSize = (int) (Math.random() * (maxNexts + 1));
			for (int i = 0; i < nextsSize; i++) {
				Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
				e.nexts.add(next);
				genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
			}
		}

		public static void main(String[] args) {
			int maxLevel = 4;
			int maxNexts = 7;
			int maxHappy = 100;
			int testTimes = 100000;
			for (int i = 0; i < testTimes; i++) {
				Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
				if (maxhappy1(boss) != maxhappy2(boss)) {
					System.out.println("Oops!");
				}
			}
			System.out.println("finish!");
		}
}
