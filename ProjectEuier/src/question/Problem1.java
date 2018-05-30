package question;

// 원래 컴퓨터는 계산기를 대용하기 위해 만든 물건이었다.
// 프로그래밍의 기본은 알고리즘. 알고리즘 문제도 풀면서 기본공부 하도록 하자
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
