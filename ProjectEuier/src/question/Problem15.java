package question;

import java.math.BigInteger;

// 20*20격자에서 왼쪽 위 모서리에서 오른쪽 아래 모서리까지 이동하는 경로(최단거리)의 총 수는?

public class Problem15 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		// 20*20 격자 초기화 필요없음. 애초에 Factorial 구하는 문제이므로
		// r이 오른쪽 이동, d가 아래 이동이라 했을때 2*2의 격자의 경우 rrdd, rdrd, rddr, drrd, drdr, ddrr 총 갯수 6개
		// 즉 rrdd에 대한 순열을 구하는 문제가 된다.
		// 계산 공식은 (r갯수+d갯수)! / (r갯수)!*(d갯수)! (2*2의 경우 = 6)
		
		// 수가 너무 크므로 BigInteger사용. Python이라면 큰 수 사용에 제약이 없으므로 그냥 써도됨 변수 형태 처음부터 지정하지도 않고...
		BigInteger result = factorial(40).divide((factorial(20).multiply(factorial(20))));
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
