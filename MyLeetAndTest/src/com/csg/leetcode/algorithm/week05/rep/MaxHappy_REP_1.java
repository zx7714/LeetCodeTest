package com.csg.leetcode.algorithm.week05.rep;

import com.csg.leetcode.algorithm.week05.MaxHappy.Employee;

public class MaxHappy_REP_1 {
	public static int maxHappy1(Employee boss) {
		if(boss == null) {
			return 0;
		}
		return process1(boss,false);
	}

	private static int process1(Employee cur, boolean b) {
		if(b) {
			int p1 = 0;
			for(Employee e : cur.nexts) {
				p1 += process1(e, false);
			}
			return p1;
		}else {
			int p1 = cur.happy;
			int p2 = 0;
			for(Employee e : cur.nexts) {
				p2 += process1(e, false);
				p1 += process1(e, true);
			}
			return Math.max(p1, p2);
		}
	}

	public static class Info{
		int yes;
		int no;
		public Info(int yes,int no) {
			this.yes = yes;
			this.no = no;
		}
	}
	
	public static int maxHappy2(Employee boss) {
		if(null == boss) {
			return 0;
		}
		Info info = process2(boss);
		return Math.max(info.yes, info.no);
	}

	private static Info process2(Employee cur) {
		if(cur.nexts == null) {
			return new Info(cur.happy,0);
		}
		int p1 = cur.happy;
		int p2 = 0;
		for (Employee emp : cur.nexts) {
			Info info = process2(emp);
			p2 += Math.max(info.no, info.yes);
			p1 += info.no;
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
					if (maxHappy1(boss) != maxHappy2(boss)) {
						System.out.println("Oops!");
					}
				}
				System.out.println("finish!");
			}
}
