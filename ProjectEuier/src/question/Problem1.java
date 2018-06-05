package question;

// 프로그래밍의 기본은 알고리즘. 알고리즘 문제도 풀면서 기본공부 하도록 하자
// 1000보다 작은 자연수 중에서 3 또는 5의 배수를 모두 더하면 얼마일까요?

public class Problem1 {
	public static void main(String args[]) {
		int result = 0;
		for(int i = 1; i < 1000; i++) {
			if(i%3 == 0 || i%5 == 0) {
				result += i;
			}
		}
		System.out.println(result);
	}
}
