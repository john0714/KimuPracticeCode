package question;

import java.math.BigInteger;

// 100! 의 자리수를 모두 더하면?

public class Problem20 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		BigInteger hundredfac = factorial(100); // 2의 0승
		
		//자릿수 모두 더하기
		String Stringconvert = String.valueOf(hundredfac);
		int result = 0;
		
		for(int i = 0; i < Stringconvert.length(); i++) {
			result += Integer.valueOf(Stringconvert.substring(i, i+1));
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	//factorial method
		public static BigInteger factorial(int number) {

			BigInteger temp = new BigInteger("1");  // BigInteger 초기화
			
			for(int i = number; i > 0 ; i--) {
				temp = temp.multiply(BigInteger.valueOf(i));
			}
			
			return temp;
		}
}
