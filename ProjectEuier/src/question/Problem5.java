package question;

public class Problem5 {
	public static void main(String args[]) {
		int i = 0;
		int number = 1;
		
		while(true) {
			boolean check = true;
			
			for(i = 1 ; i<=20 ; i++) {
				if(number%i != 0) {
					check = false;
					break;
				}
			}
			
			if(check) {
				System.out.println(number);
				break;
			}
			number++;
		}
	}
}
