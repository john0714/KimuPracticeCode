package question;

import java.math.BigInteger;

// 2^1000의 자리수를 전부 더하면 얼마입니까?

public class Problem16 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		int count = 1;
		
		BigInteger multiplier = new BigInteger("1"); // 2의 0승
		
		while(count <= 1000) {
			multiplier = multiplier.multiply(BigInteger.TWO);  // 2를 곱함
			count++;
		}
		
		String Stringconvert = String.valueOf(multiplier);
		int result = 0;
		
		for(int i = 0; i < Stringconvert.length(); i++) {
			result += Integer.valueOf(Stringconvert.substring(i, i+1));
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
