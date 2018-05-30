package question;

public class Problem6 {
	public static void main(String args[]) {
		int squareadd = 0;
		int addsquare = 0;
		
		for(int i = 1 ; i <= 100 ; i++) {
			squareadd += (int) Math.pow(i, 2);  // 실수에서 정수로 변환(double -> int)
			addsquare += i;
		}
		
		System.out.println((int)Math.pow(addsquare, 2) - squareadd);
	}
}
