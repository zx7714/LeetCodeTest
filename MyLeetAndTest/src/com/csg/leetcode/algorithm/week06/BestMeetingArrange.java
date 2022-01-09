package com.csg.leetcode.algorithm.week06;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 会议安排问题，给定一组会议，有结束时间和开始时间，找出能参与最多的会议方案。（只能参加一个会议）
 * @author zhangxu
 *
 */
public class BestMeetingArrange {
	
	public static class Meeting{
		public int start;
		public int end;
		public Meeting(int start,int end) {
			this.start =start;
			this.end = end;
		}
	}
	//
	public static int bestArrage1(Meeting[] meet) {
		if(null == meet || meet.length == 0) {
			return 0;
		}
		return process(meet,0,0);
		
	}
	
	
	/**
	 * 穷举，全排列，找到所有组合方式，选取最优解
	 * @param meet 剩余的会议
	 * @param done 已经参加过的次数
	 * @param timeLine 最晚的结束时间
	 * @return
	 */
	private static int process(Meeting[] meet, int done, int timeLine) {
		if(null == meet || meet.length == 0) {
			return done;
		}
		int max = done;
		for(int i = 0 ; i < meet.length;i++) {
			if(meet[i].start >= timeLine) {
				//剩下的会议
				Meeting[] copy = copyMeetingButExpect(meet,i);
				max = Math.max(process(copy, done+1, meet[i].end),max);
			}
		}
		return max;
	}



	private static Meeting[] copyMeetingButExpect(Meeting[] meet, int i) {
		Meeting[] copy = new Meeting[meet.length-1];
		int index = 0;
		for(int k = 0;k<meet.length;k++) {
			if(k != i) {
				copy[index++] = meet[k];
			}
		}
		return copy;
	}
	
	
	/**
	 * 贪心算法，优先安排结束时间早的
	 * @param meets
	 * @return
	 */
	public static int bestArrage2(Meeting[] meets) { 
		Arrays.sort(meets, new MeetComparetor());
		int max = 0;
		int timeLine = 0;
		for(Meeting meet : meets) {
			if(meet.start < timeLine) {
				continue;
			}
			max ++;
			timeLine = meet.end;
		}
		return max;
		
	}


	public static class MeetComparetor implements Comparator<Meeting>{

		@Override
		public int compare(Meeting o1, Meeting o2) {

			return o1.end -o2.end;
		}
		
	}

	// for test
	public static Meeting[] generatePrograms(int programSize, int timeMax) {
		Meeting[] ans = new Meeting[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Meeting(r1, r1 + 1);
			} else {
				ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int programSize = 14;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Meeting[] programs = generatePrograms(programSize, timeMax);
			int bestArrage1 = bestArrage1(programs);
			int bestArrage12 = bestArrage2(programs);
			if (bestArrage1 != bestArrage12) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
