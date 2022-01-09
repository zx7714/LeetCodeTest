package com.csg.test.usetest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SafeOfStringBuffer {
	public static void main(String args[]) throws InterruptedException{
		StringBuffer s = new StringBuffer();
		s.append("123");
		s.append("456");
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(4,5,60,TimeUnit.SECONDS,new ArrayBlockingQueue<>(50));
	}
}
