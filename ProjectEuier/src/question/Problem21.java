package question;

// n의 약수들 중 자신을 제외한 것의 합을 d(n)이라고 할때, 서로 다른 두 정수 a,b에 대하여 d(a) = b이고 d(b) = a라면
// a, b는 친화쌍이라 하고 a와 b를 각각 친화수 라고함
// 10000 이하 친화수들의 합은?
// (단, 1은 포함하지 않음)

public class Problem21 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		int[] check = new int[10000];
		int init = 0;
		while(init < 10000) { // 중복된 수 체크를 위해 배열을 만들어서 체크함
			check[init] = 0;  // 0 ~ 9999
			init++;
		}
		
		int result = 0;
		int count = 2;
		
		while(count <= 10000) {
			int sum = getsum(count); // 자신을 제외한 약수의 합(진약수)
			
			if(sum < 10000) {
				int sum2 = getsum(sum);
				
				if(count == sum2 && sum != sum2) { // 친화쌍이 같은 수일경우 제외(ex:6의 경우 친화쌍도 6이므로 제외)
					check[count-1] = 1;
				}
			}
			count++;
		}
		
		for(int i = 0; i<10000; i++) {
			if(check[i] == 1) {
				result += i+1;
			}
		}

		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	//자신을 제외한 약수의 합을 구하는 메소드
	public static int getsum(int count) {
		int sum = 0;
		int right = count;
		
		for(int left = 1; left < right; left++) {
			if(count%left == 0) {
				right = count/left;
				sum += left; // 자신을 제외한 약수의 왼쪽 수
				if(left > 1 && left != right) {
					sum += right; // 자신을 제외한 약수의 오른쪽 수
				}
			}
		}
		
		return sum;
	}
}
