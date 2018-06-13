package question;

import java.math.BigInteger;
import java.util.*;

// 어떤 대상을 순서에 따라 배열한 것을 순열이라고 합니다. 예를들어 3124는 숫자 1,2,3,4로 만들수 있는 순열 중 하나입니다.
// 이렇게 만들 수 있는 모든 순열을 숫자나 문자 순으로 늘어놓은 것을 사전식 순열이라고 합니다.
// 예) 0,2,3로 만들수 있는 사전식 순열 : 012 021 102 120 201 210
// 그럼 0, 1, 2, 3, 4, 5, 6, 7, 8, 9로 만들 수 있는 1,000,000번째 사전식 순열은?

// 딱봐도 factorial문제다.
// 총 경우의 수 10! = 362800
// 9! = 362880이고 0,1,2...의 순서대로 진행하므로 1,000,000번째 사전식 순열일때 앞의 값은 2다. 이런식으로 알고리즘 짜서 진행

public class Problem24 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();

		//init(숫자갯수, 0부터시작)
		int max = 10; // 0 ~ 9
		int position = 1000000; // 찾고자 하는 위치
		String result = "";
		List number = new ArrayList<Integer>();
		for(int i = 0; i < max; i++) {
			number.add(i);
		}
		
		if(position < factorial(max).intValue()) {
			position = position-1; //위치가 0번부터 시작이므로 실제 100만번째 위치를 알기위해선 -1을 해줘야함
			for(int i = 1; i < max; i++) {
				BigInteger BI = factorial(max-i);
				int BIV = BI.intValue();
				result += String.valueOf(number.get(position/BIV)); // 숫자 입력
				number.remove(position/BIV); // 입력한 숫자 삭제
				position = position%BIV;
			}
			result += String.valueOf(number.get(0)); //마지막으로 남은 숫자 입력
			System.out.println(result);
		} else {
			System.out.println("찾으려는 위치가 사전식 순열의 범위를 벗어났습니다.");
		}
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	/*
	 * factorial method
	 * input : int
	 * output : BigInteger
	 */
	public static BigInteger factorial(int number) {

		BigInteger temp = new BigInteger("1");  // BigInteger 초기화
			
		for(int i = number; i > 0 ; i--) {
			temp = temp.multiply(BigInteger.valueOf(i));
		}
			
		return temp;
	}
}
