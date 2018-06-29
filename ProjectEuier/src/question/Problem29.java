package question;

import java.util.*;
import java.math.*;

/*
 * 2 ≤ a ≤ 100 이고 2 ≤ b ≤ 100인 a, b로 만들 수 있는 a^b의 개수(중복제외)
 */

public class Problem29 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		int a = 2;
		int count = 0;
		
		List check = new ArrayList();
		
		while(a <= 100) {
			
			int b = 2;
			while(b <= 100) {
				if(!check.contains(getpow(a,b))) {
					check.add(getpow(a,b));
					count++;
				}
				b++;
			}
			a++;
		}
		
		System.out.println(count);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	// 거듭제곱수 구하는 함수
	public static BigInteger getpow(int a, int b) {
		BigInteger result = BigInteger.ONE; //init
		
		for(int i = 0; i < b; i++) {
			result = result.multiply(BigInteger.valueOf(a));
		}
		
		return result;
	}
}
