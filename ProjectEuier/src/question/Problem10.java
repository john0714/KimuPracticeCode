package question;

// 10이하의 소수를 모두 더하면 2 + 3 + 5 + 7 = 17이 됩니다
// 200만 이하의 소수를 모두 더하면 얼마입니까?

public class Problem10 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		//에라토스테네스의 체 사용
		//이런식으로 쭉 배수들을 지워내면됨
		int max = 2000000;
		long sum = 2; //2는 소수 이므로 2의배수(=짝수)는 소수가 아님, 따라서 2제외이므로 초기값 2로 지정
		// sum값을 int로 지정하면 32비트 최대치밖에 안들어가서 1179908154의 오답이 나오게됨, long로 지정해서 값을 더하도록 하자
		
		boolean[] primearray = new boolean[max/2]; // 홀수만 지정
		for(int i = 0; i < max/2 ; i++) { //init
			primearray[i] = true;
		}
		
		for(int i = 1; i < max/2; i++) { //1은 제외
			if(primearray[i]) {
				sum += i*2+1; //짝수 전원 제외후 계산
				
				int temp = i*2+1;
				int count = 2;
				while(temp*count < max) { //소수의 배수 전부 제외
					if((temp*count-1)%2 == 0) {
						primearray[(temp*count-1)/2] = false;
					}
					count++;
				}
			}
		}
		
		System.out.println(sum);

		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
