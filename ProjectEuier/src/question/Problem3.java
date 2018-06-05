package question;

// 가장 큰 소인수 구하기
// 15가 안된다.　적어도 하나는 제곱근보다 작다는 식이므로, 이 소인수 구하기 알고리즘은 틀렸다. 다시 알고리즘 짜야한다
// 반대쪽도 소인수인지 체크가 필요하다.
// 혹은 2부터 시작해서 나눠 떨어질 경우 값을 올려서 체크하는 알고리즘으로 구해도 됨

public class Problem3 {
	public static void main(String args[]) {
		long result = 0;
		long num = 600851475143L;  //엄청게 큰 수는 long형태로 숫자 뒤에 L을 넣음
		double sqrtnum = Math.sqrt(num);  //특정 수의 소인수 중 적어도 하나는 그 수의 제곱근의 수를 넘지 않는다.
		for(int i = 2 ; i < sqrtnum; i++) {
			if(num%i == 0) {
				boolean check = true;
				boolean rcheck = true;
				long ri = num/i;
				for(int j = 2 ; j < i-1 ; j++) {
					if(i%j == 0) {
						check = false;
						break;
					}
				}
				
				if(check) result = i;
				
				// 다른 한쪽도 소인수인지 체크
				for(int j = 2 ; j < ri-1 ; j++) {
					if(ri%j == 0) {
						rcheck = false;
						break;
					}
				}
				
				if(rcheck && ri > result) {
					result = ri;
				}
			}
			System.out.println("作業中:"+i);
		}
		System.out.println(result);
	}
}
