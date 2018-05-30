package question;

public class Problem3 {
	public static void main(String args[]) {
		int result = 0;
		long num = 600851475143L;  //엄청게 큰 수는 long형태로 숫자 뒤에 L을 넣음
		double sqrtnum = Math.sqrt(num);  //특정 수의 소인수 최대값은 그 수의 제곱근의 수를 넘지 않는다.特定数の素因数の最大はその特定数の平方根を超えない。
		for(int i = 2 ; i < sqrtnum; i++) {
			if(num%i == 0) {
				boolean check = true;
				for(int j = 2 ; j < i-1 ; j++) {
					if(i%j == 0) {
						check = false;
						break;
					}
				}
				if(check) result = i;
			}
			System.out.println("作業中:"+i);
		}
		System.out.println(result);
	}
}
