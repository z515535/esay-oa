package org.gzccc.oa.tools; 

import java.util.Random;

/** 
 * @author 作者 吴 耿锋: 
 * @version 创建时间：2016年5月12日 下午5:37:29 
 * 类说明 盐值生成器
 */
public class SaltGenerator {
	private SaltGenerator(){}
	
	//生成位数
	private static final int DIGIT = 15;
	//ASCII起始
	private static final int ASCII_MIN = 48;
	private static final int ASCII_MAX = 125;
	
	//单例模式
	private static class SingleRandom{
		private static final Random RANDOM = new Random();
		private SingleRandom(){}
		public static final Random getRandom(){
			return SingleRandom.RANDOM;
		}
	}
	
	/**
	 * 生成盐值
	 * @return
	 */
	public static String generator(){
		StringBuilder salt = new StringBuilder();
		Random random = SingleRandom.getRandom();
		char temp;
		for(int i=0; i<DIGIT; i++){
			temp = (char) (random.nextInt(ASCII_MAX - ASCII_MIN + 1) + ASCII_MIN);
			salt.append(temp);
		}
		return salt.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(SaltGenerator.generator());
	}
}
 