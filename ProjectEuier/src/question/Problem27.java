package question;

/*
 * 연속되는 n에 대해 가장 많은 소수를 만들어내는 2차식 구하기
 * n^2 + an + b(단, a < 1000, b < 1000)에서 연속된 n에 대해 가장 많은 소수를 만들어내는 2차식을 찾아서 그 계수 a와b의 곱을 구하기
 */

// 기본적인 산수만 생각해서 코드 짯으므로 시간 좀 걸(약 2.6초)
public class Problem27 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		int a = -999;
		int rcheck = 0;
		long result = 0;
		long checknumber = 0;
		
		while(a < 1000) {
			int b = -999;
			while(b < 1000) {
				int count = 0;
				int n = 0;
				while(true) {
					checknumber = n*n+a*n+b;
					if(checkpnum(checknumber)) {
						count++;
						n++;
						continue;
					} else {
						break;
					}
				}
				
				if(rcheck < count) {
					rcheck = count;
					result = a*b;
				}
				
				b++;
			}
			a++;
		}
		
		System.out.println(rcheck);
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	/*
	 * prime number check
	 * input : long
	 * output : boolean
	 */
	public static boolean checkpnum(long number) {
		if(number <= 1 ) return false;
		boolean check = true;
		
		for(int i = 2; i < number; i++) {
			if(number%i == 0) {
				check = false;
				break;
			}
		}
		
		return check;
	}
}
