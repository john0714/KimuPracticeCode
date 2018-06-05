package question;

// 50자리 숫자 100개, 이걸 모두 더한 값의 첫 10자리는?

public class Problem14 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		int Ccheck = 0;
		long result = 0; // 자바의 경우 int로 했을때 113383에서 뻗어버리므로 체크하는 값을 long으로 해야함
		
		for(long i = 1; i <= 1000000; i++) {
			int count = 0;
			long temp = i;
			
			while(temp != 1) {
				if(temp%2 == 0) temp = temp/2;
				else temp = temp*3 + 1;
				count++;
			}
			
			if(Ccheck < count) {
				Ccheck = count;
				result = i;
			}
		}
		
		System.out.println(result);

		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
