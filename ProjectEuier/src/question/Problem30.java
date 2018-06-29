package question;

import java.util.*;
import java.math.*;

/*
 * 각 자리 숫자를 5제곱해서 더했을 때 자기 자신이 되는 수들의 합은?
 */

public class Problem30 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		int a = 2; //1제외
		int result = 0;
		
		// 6자리 수 일때 최대 합은 (9^5)*6 = 354294
		// 7자리 수 일때 최대 합은 (9^5)*7 = 413343으로 6자리의 수를 넘지 않는다.
		// 즉, 7자리 이상일 경우 합이 자기 자신을 못넘으므로, 상한은 6자리 수의 최대값 354294로 잡으면 된다.
		// 앞으로는 상한을 모를때 일단 최대값을 생각해보도록 하자.
		while(a < 354294) {
			int temp = 0;
		
			for(int i = 0; i < String.valueOf(a).length(); i++) {
				String word = String.valueOf(a).substring(i, i+1);
				temp += getpow(Integer.valueOf(word));
			}
			
			if(temp == a) {
				result += temp;
			}
			
			a++;
		}
		
		System.out.println(result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	// 5제곱 구하는 함수
	public static long getpow(int a) {
		long result = 1; //init
		
		for(int i = 0; i < 5; i++) {
			result = result*a;
		}
		
		return result;
	}
}
