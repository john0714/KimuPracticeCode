package question;

//a + b + c = 1000 이 되는 피타고라스 수

public class Problem9 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		int a = 1; 
		int b = 1;
		int c;
		// 피타고라스. 9번부터 진행
		while(true) {
			c = a*a + b*b;
			
			if((int)Math.sqrt(c)-Math.sqrt(c) == 0) { // 제곱근이 정수인지 확인
				if(a+b+(int)Math.sqrt(c) == 1000) {
					System.out.println(a*b*(int)Math.sqrt(c));
					break;
				}
			}
			
			if(a+b+(int)Math.sqrt(c) < 1000) {
				b++;
			} else {
				a++;	
				b = a+1; // (a < b < c)
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
