package com.csg.leetcode.algorithm.week02;

import com.csg.leetcode.algorithm.SortUtlis;
//大根堆
public class HeapBig {
	private int[] heap;
	private int heapSize = 0;
	private int max;
	
	public HeapBig(int max) {
		heap = new int[max];
		this.max = max;
	}
	
	public void push(int val) {
		if(heapSize == max) {
			throw new RuntimeException("堆满了");
		}
		heap[heapSize] = val;
		heapInsert(heap,heapSize++);
	}
	
	public int pop() {
		if(heapSize == 0) {
			throw new RuntimeException("堆空了");
		}
		int res = heap[0];
		SortUtlis.swap(heap, 0, --heapSize);
		heapify(heap, 0, heapSize);
		return res;
	}
	
	private void heapInsert(int[] heap, int index) {
		while(heap[index] > heap[(index -1) >> 1]) {
			SortUtlis.swap(heap, index, (index-1) >> 1);
			index = (index-1) >> 1;
		}
	}
	//从index位置不断往下看，遇见大的往下沉。
	//没有孩子比自己大的时候，停！！！
	private void heapify(int[] heap,int index,int heapSize) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			//左右两个孩子谁大把谁的下标给lagest
			//右 ——> 1）右必须有孩子; 2)右比左。否则左。
			int lagest = left+1 < heapSize && heap[left] < heap[left+1] ? left+1 : left;
			lagest = heap[index] < heap[lagest] ? lagest : index;
			if(lagest == index) {
				break;
			}
			SortUtlis.swap(heap, lagest, index);
			index = lagest;
			left = index * 2 +1;
		}
	}
	
	
}
