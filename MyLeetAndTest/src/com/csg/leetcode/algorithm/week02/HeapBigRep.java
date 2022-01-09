package com.csg.leetcode.algorithm.week02;

import com.csg.leetcode.algorithm.SortUtlis;

public class HeapBigRep {
	private int [] heap;
	private int limit;
	private int heapSize;
	public HeapBigRep(int limit) {
		heap = new int[limit];
		this.limit = limit;
	}
	
	public void push(int val) {
		if(heapSize == limit) {
			throw new RuntimeException("full ...");
		} 
		heap[heapSize] = val;
		heapInsert(heap,heapSize++);
		
	}
	
	private void heapInsert(int[] heap, int index) {
		while(heap[(index -1) >> 1] < heap[index]) {
			SortUtlis.swap(heap, index, (index-1) >> 1);
			index = (index-1) >> 1;
		}
	}

	public int pop() {
		if(heapSize == 0) {
			throw new RuntimeException("empty ...");
		}
		int ans = heap[0];
		SortUtlis.swap(heap, 0, heapSize--);
		heapify(heap,0,heapSize);
		return ans;
	}

	private void heapify(int[] heap, int index, int heapSize) {
		int left = 2 * index + 1;
		while(left < heapSize) {
			int lagest = left + 1 < heapSize && heap[left] < heap[left+1] ? left + 1 : left;
			lagest = heap[lagest] < heap[index] ? index : lagest;
			if(lagest == index) {
				break;
			}
			SortUtlis.swap(heap, lagest, index);
			index = lagest;
			left = 2 * index + 1;
		}
	}
}
