package question;

//10001번째의 소수

public class Problem7 {
	public static void main(String args[]) {
		int primenumbercount = 0;
		int count = 2;
		while(true) {
			boolean check = true;
			for(int j = 2 ; j <= Math.sqrt(count) ; j++) {
				if(count%j == 0) {
					check = false;
					break;
				}
			}
			
			if(check) {
				primenumbercount++;
			}
			
			if(primenumbercount == 10001) {
				System.out.println(count);
				break;
			}
			count++;
		}
	}
}
