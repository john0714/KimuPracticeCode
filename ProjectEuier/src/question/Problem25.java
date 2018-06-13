package question;

import java.math.BigInteger;

// 피보나치 수열에서 처음으로 1000자리가 되는 항은 몇 번째?

public class Problem25 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();

		BigInteger one = new BigInteger("1"); //첫번째 항
		BigInteger two = new BigInteger("1"); //두번째 항
		String Fibonacci = "";
		int count = 2;
		
		while(Fibonacci.length() < 1000) { //자릿수
			BigInteger F = one.add(two);
			count++;
			Fibonacci = String.valueOf(F);
			one = two;
			two = F;
		}
		
		System.out.println(count);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
