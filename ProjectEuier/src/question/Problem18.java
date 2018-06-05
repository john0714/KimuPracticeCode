package question;

import java.util.*;

// 삼각형 숫자에서 합이 최대가 되는 경로 찾기
// 출발점에서 시작해서 좌우로 가는길에 삼각형이 있는데 어디로 이동 할지는 아래단계의 좌 우 삼각형의 합을 구해서 더 큰쪽으로 정하면 된다.
// 3층 이상의 삼각형이면 값이 커서 계산하기 힘들지만 2층짜리 삼각형이라면 바로 계산 가능하다. 이 알고리즘을 사용하여 계산 코드를 구현해보자

public class Problem18 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();

		//List triple = init();
		List triple = init();
		
		// init first value
		int nextlevel[] = getnextlevel((int[])triple.get(0), (int[])triple.get(1));
		 
		// 계속 값을 더하면서 위로 올려서 왼쪽과 오른쪽의 값을 구해서 큰 값이 합이 최대가 되는 경로의 합이 된다.
		// 초기값 이후의 값부터 계산 시작
		for(int i = 2; i < triple.size(); i++) {
			nextlevel = getnextlevel(nextlevel, (int[])triple.get(i));
		}
		
		System.out.println(nextlevel[0]);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	// set init
	private static List init() {
		List triple = new ArrayList<int[]>();
	
		int a[] = {75};
		int b[] = {95,64};
		int c[] = {17,47,82};
		int d[] = {18,35,87,10};
		int e[] = {20,04,82,47,65};
		int f[] = {19,01,23,75,03,34};
		int g[] = {88,02,77,73,07,63,67};
		int h[] = {99,65,04,28,06,16,70,92};
		int i[] = {41,41,26,56,83,40,80,70,33};
		int j[] = {41,48,72,33,47,32,37,16,94,29};
		int k[] = {53,71,44,65,25,43,91,52,97,51,14};
		int l[] = {70,11,33,28,77,73,17,78,39,68,17,57};
		int m[] = {91,71,52,38,17,14,91,43,58,50,27,29,48};
		int n[] = {63,66,04,68,89,53,67,30,73,16,69,87,40,31};
		int o[] = {04,62,98,27,23,9,70,98,73,93,38,53,60,04,23};
		
		triple.add(o);
		triple.add(n);
		triple.add(m);
		triple.add(l);
		triple.add(k);
		triple.add(j);
		triple.add(i);
		triple.add(h);
		triple.add(g);
		triple.add(f);
		triple.add(e);
		triple.add(d);
		triple.add(c);
		triple.add(b);
		triple.add(a);
		
		return triple;
	}

	// 위쪽 레벨 구하는 함수
	public static int[] getnextlevel(int[] one, int[] two) { // one이 아래, two가 위
		int left[] = new int[two.length]; // 좌측 합
		int right[] = new int[two.length]; // 우측 합
		int newlevel[] = new int[two.length]; //다음 레벨
		
		for(int i = 0; i < two.length; i++) {
			left[i] = one[i] + two[i];
			right[i] = one[i+1] + two[i];
			
			if(left[i] > right[i]) {
				newlevel[i] = left[i];
			} else {
				newlevel[i] = right[i];
			}
		}
		
		return newlevel; // 배열 반환
	}
}
