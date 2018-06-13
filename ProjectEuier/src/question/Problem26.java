package question;

import java.util.*;

/*
 * 분자가 1인 분수를 단위분수라고 합니다(1/2, 1/3, 1/4, 1/5, ...)
 * 이때 1/3은 0.33333.... = 0.3, 1/6은 0.1666666.... = 0.16(6이 순환마디) 이런식으로 순환마디를 가지게 됩니다
 * 그리고 특이하게도 1/7은 0.142857142857142857.... 142857의 순환마디를 갖게 됩니다.
 * 그럼 1000 이하의 d 중에서 1/d 이 가장 긴 순환마디를 갖는 수는?
 */


// 순환마디가 어느정도의 길이일지는 파악 불가능
// 순환마디 부터 파악 해야함
// 소수점 이하의 나누기를 손으로 하는 경우, 자리수를 계속 내려가면서 나누는데 앞자리의 나머지가 다음자리의 몫이되는 것이 반복된다.
// 매 순간의 (몫, 나머지)쌍을 기록하다가 그게 이전에 나온적 있는지, 몇번째 전에 나왔는지를 알면 그게 순환마디의 길이가 된다.

public class Problem26 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		int result = 0;
		
		//우선 2의 배수(2,4,6,8,10...)와 5의 배수(5,10,15...)는 순환이 없으므로(나눠 떨어지므로) 제외
		//즉, 끝자리가 1, 3, 7, 9로 떨어지는 수만 계산하면 된다.
		for(int i = 1; i <= 1000; i++) {
			if(!(i%2 == 0 || i%5 == 0)) {// 끝자리가 1, 3, 7, 9로 끝나는 수(10, 20...이렇게 구한 수에 끝자리만 1,3,7,9붙이는게 시간 더 적게걸릴듯)
				if(result < getlength(i)) result = i;
			}
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	/*
	 * 순환마디 길이 구하는 함수
	 * input : int (1/d의 d)
	 * output : int (1/d했을때 소수의 순환마디 길이)
	 */
	public static int getlength(int number) {
		List checkArray = new ArrayList<Integer>();
		int quotient = 0; //몫 초기화
		int remainder = 1; //나머지(처음 나누 수 1)

		//이하 한 페어
		while(true) {
			List temp = new ArrayList<Integer>();
			
			if(remainder/number >= 1 ) {
				quotient = remainder/number;
				remainder = remainder%number;
			} else {
				quotient = (remainder*10)/number;
				remainder = (remainder*10)%number;
			}
			
			temp.add(quotient);
			temp.add(remainder);
			
			//check
			for(int i = 0; i < checkArray.size(); i++) {
				if(checkArray.get(i).equals(temp)) {
					return checkArray.size();
				}
			}
			checkArray.add(temp);
		}
	}
}
