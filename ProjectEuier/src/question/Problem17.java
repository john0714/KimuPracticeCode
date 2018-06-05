package question;

// 1부터 1000까지 영어로 썼을 때 사용된 글자의 개수는?
// 실제로는 1~9, 11~19, 20~99, 100(and) ~ 999 나눠서 각각 만들면 되는데, 스펠링이랑 and 붙이는거랑 one thousand때문에 시간 조금 걸리고 긴 코드 만들어짐

public class Problem17 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		int count = 1;
		long result = 0;
		
		System.out.println(11/10);
		System.out.println(191/100);
		
		//변환 함수 그냥 만듬
		while(count <= 1000) {
		    String answer = changeword(count);
			result += answer.length();  // 1 ~ 1000 하이픈이랑 공백은 그냥 빼고 and는 그냥 넣음
			count++;
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	public static String changeword(int number) {
		String result = "";
		
		if(String.valueOf(number).length() == 4) {
			result = "onethousand";
		} else if(String.valueOf(number).length() == 3) {
			switch(number/100) { //10의 자리, 공백이랑 하이픈(-)은 그냥 처음부터 안넣음
			case 1:
				result += "onehundred";
				break;
			case 2:
				result += "twohundred";
				break;
			case 3:
				result += "threehundred";
				break;
			case 4:
				result += "fourhundred";
				break;
			case 5:
				result += "fivehundred";
				break;
			case 6:
				result += "sixhundred";
				break;
			case 7:
				result += "sevenhundred";
				break;
			case 8:
				result += "eighthundred";
				break;
			case 9:
				result += "ninehundred";
				break;
			}
			
			number = number%100; // 2자릿수 이하만 남김
			if(number != 0) {
				result += "and";
			}
			
			if(number < 20) { // 1~19 까지
				switch(number) {
				case 1:
					result += "one";
					break;
				case 2:
					result += "two";
					break;
				case 3:
					result += "three";
					break;
				case 4:
					result += "four";
					break;
				case 5:
					result += "five";
					break;
				case 6:
					result += "six";
					break;
				case 7:
					result += "seven";
					break;
				case 8:
					result += "eight";
					break;
				case 9:
					result += "nine";
					break;
				case 10:
					result += "ten";
					break;
				case 11:
					result += "eleven";
					break;
				case 12:
					result += "twelve";
					break;
				case 13:
					result += "thirteen";
					break;
				case 14:
					result += "fourteen";
					break;
				case 15:
					result += "fifteen";
					break;
				case 16:
					result += "sixteen";
					break;
				case 17:
					result += "seventeen";
					break;
				case 18:
					result += "eighteen";
					break;
				case 19:
					result += "nineteen";
					break;
				}
			} else {
				switch(number/10) { //10의 자리
				case 2:
					result += "twenty";
					break;
				case 3:
					result += "thirty";
					break;
				case 4:
					result += "forty";
					break;
				case 5:
					result += "fifty";
					break;
				case 6:
					result += "sixty";
					break;
				case 7:
					result += "seventy";
					break;
				case 8:
					result += "eighty";
					break;
				case 9:
					result += "ninety";
					break;
				}
				
				switch(number%10) { // 1의 자리
				case 1:
					result += "one";
					break;
				case 2:
					result += "two";
					break;
				case 3:
					result += "three";
					break;
				case 4:
					result += "four";
					break;
				case 5:
					result += "five";
					break;
				case 6:
					result += "six";
					break;
				case 7:
					result += "seven";
					break;
				case 8:
					result += "eight";
					break;
				case 9:
					result += "nine";
					break;
				}
			}
		} else if(String.valueOf(number).length() == 2) { // 두자리 수
			if(number < 20) { // 10~19 까지
				switch(number) {
				case 10:
					result = "ten";
					break;
				case 11:
					result = "eleven";
					break;
				case 12:
					result = "twelve";
					break;
				case 13:
					result = "thirteen";
					break;
				case 14:
					result = "fourteen";
					break;
				case 15:
					result = "fifteen";
					break;
				case 16:
					result = "sixteen";
					break;
				case 17:
					result = "seventeen";
					break;
				case 18:
					result = "eighteen";
					break;
				case 19:
					result = "nineteen";
					break;
				}
			} else {
				switch(number/10) { //10의 자리
				case 2:
					result += "twenty";
					break;
				case 3:
					result += "thirty";
					break;
				case 4:
					result += "forty";
					break;
				case 5:
					result += "fifty";
					break;
				case 6:
					result += "sixty";
					break;
				case 7:
					result += "seventy";
					break;
				case 8:
					result += "eighty";
					break;
				case 9:
					result += "ninety";
					break;
				}
				
				switch(number%10) { // 1의 자리
				case 1:
					result += "one";
					break;
				case 2:
					result += "two";
					break;
				case 3:
					result += "three";
					break;
				case 4:
					result += "four";
					break;
				case 5:
					result += "five";
					break;
				case 6:
					result += "six";
					break;
				case 7:
					result += "seven";
					break;
				case 8:
					result += "eight";
					break;
				case 9:
					result += "nine";
					break;
				}
			}
			
		} else { // 한자리 수
			switch(number) {
				case 1:
					result = "one";
					break;
				case 2:
					result = "two";
					break;
				case 3:
					result = "three";
					break;
				case 4:
					result = "four";
					break;
				case 5:
					result = "five";
					break;
				case 6:
					result = "six";
					break;
				case 7:
					result = "seven";
					break;
				case 8:
					result = "eight";
					break;
				case 9:
					result = "nine";
					break;
			}
		}
		
		return result;
	}
}
