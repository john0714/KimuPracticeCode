package question;

public class Problem4 {
	public static void main(String args[]) {
		int num1 = 900;
		String result = "0";
		String rcheck = "";
		
		while(num1<1000) {
			int num2 = 900;
			while(num2<1000) {
				boolean check = true;
				rcheck = String.valueOf(num1*num2);
				if(rcheck.length() < 6) {
					num2++;
					continue;
				}
				
				//대칭수인지 아닌지 체크
				for(int i = 0; i < rcheck.length()/2 ; i++) {
					String temp1 = rcheck.substring(i, i+1);
					String temp2 = rcheck.substring(rcheck.length()-(i+1), rcheck.length()-i);
					if(!temp1.equals(temp2)) {
						check = false;
						break;
					}
				}
				if(check && Integer.parseInt(result) < Integer.parseInt(rcheck)) {
					result = rcheck;
				}
				
				num2++;
			}
			num1++;
		}
		System.out.println(result);
	}
}
