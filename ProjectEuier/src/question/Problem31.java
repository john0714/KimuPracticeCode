package question;

import java.util.*;
import java.math.*;

/*
 * 영국 화폐 액면가를 조합하는 방법의 수
 * 2파운드(200p)를 조합하는 방법 수 구하는것.
 * 1p, 2p, 5p, 10p, 20p, 50p, 100p, 200p를 써서 구하면 됨
 * 
 * 더 열심히 공부 할 필요가 있을 듯
 */

public class Problem31 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		// 처음엔 재귀함수로 풀려고 했는데 숫자가 다른 경우를 못골라내서 안됐음
		// 最初は再帰関数の解決しようと思ったが、数の順番が違う時の制限ができなかったのでダメでした。
		int sum = 200;
		int[] money = {1, 2, 5, 10, 20, 50, 100, 200}; //동전 사이즈(Coin Size)
		int[] combinations = new int[sum + 1]; // 200파운드까지 경우의 수를 넣을 배열(200までの数の配列) 
		combinations[0] = 1; // 0파운드는 아무것도 없다는 1가지 경우의 수가 있다고 지정(0の場合何もないという1つの数があると考える)
		
		for(int i = 0; i < money.length; i++) {
			for (int j = money[i]; j <= sum; j++) { // 200までの数を出す
				combinations[j] += combinations[j - money[i]]; // 구하려는 파운드에서 지정한 그 동전을 뺀 나머지 파운드의 경우의 수를 더해줌 
				// 현재 경우의 수는 현재 동전만으로 구할수 있는 경우의 수 + 이전까지 동전으로 구한 경우의 수이므로 이전까지의 경우의 수를 더함
				// 즉, 코인 1만 있을때 1~200파운드까지 구하는 경우의 수를 모두 구하고. 1,2만 있을때 1~200파운드까지 만드는 경우의 수(= 2코인만 있을때의 특정 파운드를 만들때의 경우의 수 + 특정파운드-2파운드를 2이외 나머지 코인만 있을때의 경우의 수)를 구하는... 이런 방식이다.
				// 그 동전 빼고 더 적은 파운드를 구한 경우의 수(즉, 이전까지의 수)에 그 동전만으로 그 파운드를 구한 경우의 수를 더한거다. 알고리즘 되게 신기하네
			}
		}
		
		System.out.println(combinations[sum]);
		
		
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
}
