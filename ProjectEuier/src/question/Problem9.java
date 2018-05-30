package question;

public class Problem9 {
	public static void main(String args[]) {
		int a = 1; 
		double c;
		// 피타고라스. 9번부터 진행
		while(true) {
			int b = a+1;
			c = a*a + b*b;
			
			if(a+b+Math.sqrt(c) == 1000) {
				System.out.println(a*b*c);
				break;
			}
			
			b++;
		}
	}
}
