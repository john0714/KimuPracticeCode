package question;

// 자신을 제외한 약수(진약수)를 모두 더하면 자기 자신이 되는 수(6, 28 등)을 완전수라고 함
// 진약수의 합이 자신보다 작으면 부족수, 자신보다 클 때는 초과수(12, 220 등)라고 함
// 12는 초과수중에 가장 작으므로, 초과수 두개의 합으로 나타낼 수 있는 수중 가장 작은 수는 24임
// 이미 증명된 것으로, 28123을 넘는 모든 정수는 두 초과수의 합으로 표현 가능하다고 함
// 그럼 두 초과수의 합으로 나타낼 수 없는 모든 양의 정수의 합은?

import java.util.*;

public class Problem23 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		//init
		List overnumber = new ArrayList<Integer>();
		int[] checknumber = new int[28123];
		int result = 0;
		
		int count = 12; //초과수중 가장 작은 수 12
		
		while(count < 28113) {
			if(getsum(count) > count) { // 초과수일 경우
				overnumber.add(count);; // 초과수 리스트에 추가
			}
			count++;
		}
		
		
		// 초과수 갯수 : 6962개
		int i = 0;
		while(i < 6961) { // 모든 덧셈 경우의 수
			int icount = i;
			while(icount < overnumber.size()) { //0 ~ 6961(마지막 숫자는 더할꺼 없음)
				int temp = (int)overnumber.get(i) + (int)overnumber.get(icount);
				if( temp < 28124) {
					checknumber[temp-1] = 1; // 0부터 시작
				}
				icount++;
			}
			i++;
		}
		
		count = 0;
		while(count < 28113) {
			if(checknumber[count] != 1) {
				result += count+1; // 초과수 합 목록 전부 더하기
			}
			count++;
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	/*
	 * 진약수 구하는 메소드
	 * input : int
	 * output : int
	 */
	public static int getsum(int count) {
		int sum = 0;
		int right = count;
			
		for(int left = 1; left < right; left++) {
			if(count%left == 0) {
				right = count/left;
				sum += left; // 자신을 제외한 약수의 왼쪽 수
				if(left > 1 && left != right) {
					sum += right; // 자신을 제외한 약수의 오른쪽 수
				}
			}
		}
		
		return sum;
	}
}
