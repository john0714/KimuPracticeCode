package question;

import java.util.*;

// 20세기에서 매월 1일이 일요일인 경우는 총 몇 번 인가?
// Java Calendar클래스 써서 풀긴했는데, 실제론 달력에 관한 정보를 토대로 외부클래스 안쓰고 푸는 문제임
// 근데 그냥 자바 공부 겸 클래스 써서 품

public class Problem19 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		String[] DOW = {"일","월","화","수","목","금","토"};
		
		//init
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		
		int rcount = 0;
		int ycount = 1901;
		while(ycount < 2001) {
			int mcount = 0;
			while(mcount < 12) {
				cal.set(Calendar.YEAR, ycount);
				cal.set(Calendar.MONTH, mcount); //month는 0부터 시작
				if(DOW[cal.get(Calendar.DAY_OF_WEEK)-1] == "일") { //요일체크
					rcount++;
				}
				mcount++;
			}
			ycount++;
		}
		
		System.out.println(rcount);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
