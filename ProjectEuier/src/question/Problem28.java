package question;

/*
 * 숫자 1부터 시작해서 우측으로 시계방향으로 감아 5x5행렬을 만들었을때, 대각선의 숫자를 모두 더하면 101이 된다.
 * 그럼 1001x1001행렬에서 대각선상의 숫자를 모두 더하면 얼마가 되는가?
 */

// 행렬 만들 필요도 없이. 띄우는 간격이 숫자 4개마다 +2씩 된다는걸 이용하자.
public class Problem28 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		int max = 1001*1001;
		int check = 1;
		int checkcount = 0;
		int result = 0;
		
		for(int i = 1; i <= max; i++) {
			result += i;
			i += check;
			checkcount++;
			
			if(checkcount == 4) {
				checkcount = 0;
				check += 2;
			}
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("プログラム終了までの時間 : " + (end-start)/1000.0 + "秒");
	}
}
