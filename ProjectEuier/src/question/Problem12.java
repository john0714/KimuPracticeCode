package question;

// 1부터 n까지의 자연수를 차례로 더하여 구해진 값을 삼각수라고 합니다.
// 500개 이상의 약수를 갖는 가장 작은 삼각수는?

public class Problem12 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		int tcount = 1;
		int triple = 0;
		
		while(true) {
			int aliquotcount = 0;
			triple = triple + tcount;
			
			int rightnumber = triple;
			
			//그냥 숫자 증가시키면서 나누는 알고리즘 사용
			for(int leftnumber = 1; leftnumber < rightnumber; leftnumber++) {
				if(triple%leftnumber == 0) {
					rightnumber = triple/leftnumber;
					aliquotcount = aliquotcount + 2;
				}
			}
			
			if(aliquotcount >= 500) {
				System.out.println(triple); 
				break;
			}
			else tcount++;
		}

		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
