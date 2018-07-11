package question;

import java.util.*;
import java.math.*;

/*
 * 1~9 팬디지털 곱셈식을 만들 수 있는 모든 수의 합
 * 
 * 1부터 n까지의 각 숫자를 한번씩만 써서 만들 수 있는 숫자를 팬디지털(pandigital)이라고 함
 * 예를들어 15234는 1부터 5까지의 숫자가 한번씩만 쓰였으므로 1 ~ 5 팬디지털임
 * 7254라는 숫자는 그런 면에서 특이한데, 39 * 186 = 7254라는 곱셈식을 만들 때 이것이 1 ~ 9 팬디지털 이 되기 때문
 * 이런 식으로 a * b = c가 1~9 팬디지털이 되는 모든 c의 합은 얼마인가
 * (어떤 c는 두 개 이상의 a,b쌍에 대응 될 수도 있는데, 이런 경우는 하나라고 침)
 */

public class Problem32 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
        // 우선 그냥 펜디지털 값 찾기 테스트 해봄
        boolean check = true;
        int result = 0;
      	int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
      	
      	for(int i = 100000000; i < 999999999; i++) {  
        	for(int checkcount = 1; checkcount < 10; checkcount++) {
          	String si = Integer.toString(i);
          	String scc = Integer.toString(checkcount);
         		if(si.contains(scc)) {
        			continue;
        		} else {
                	check = false;
          			break;
        		}
        	}
        
        	if(check == true) result++;
      }
         
          
		System.out.println("Pandigital Count : "+result);
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
