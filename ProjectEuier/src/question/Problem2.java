package question;

// 피보나치 수열에서 4백만 이하이면서 짝수인 항의 합

public class Problem2 {
	public static void main(String args[]) {
		int slot1 = 1;
		int slot2 = 2;
		int result = 2;
		boolean check = true;
		while(slot1 < 4000000 || slot2 < 4000000) {
			if(check == true) {
				slot1 = slot1 + slot2;
				if(slot1%2 == 0) {
					result += slot1;
				}
				check = false;
			} else {
				slot2 = slot1 + slot2;
				if(slot2%2 == 0) {
					result += slot2;
				}
				check = true;
			}
		}
		System.out.println(result);
	}
}
