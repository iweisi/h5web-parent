package com.xmbl.h5.web.util;

import java.util.Random;

public class MathUtil {
	private static final Random RANDOM = new Random();
	
	public static final long twoPow22 = (long)Math.pow(2, 22);

	/**
	 * 获取[min, max]之间的随机数
	 * @return [min, max]之间的随机数
	 */
	public static final int randomInt(int min, int max) {
		return RANDOM.nextInt(max - min + 1) + min;
	}
	
	public static void main(String[] args) {
		System.out.println(Math.pow(2, 16));
	}
}
