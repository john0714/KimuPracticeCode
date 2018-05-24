package m;
import java.util.Scanner;

public class Q3 {
	public static void main(String args[] ) {
		 String r = "";
		 int count = 1;
		 Scanner sc = new Scanner(System.in);
		 int max = sc.nextInt();
		 
		 getdata(r, count, max);
	}
	
	public static void getdata(String r, int c, int m) {
		String[] alpa = {"u","d","c","s","t","b"};
		for(int i = 0; i < alpa.length; i++) {
			if(c < m) {
				String tr = r + alpa[i];
				c++;
				getdata(tr, c, m);
				c--;
			} else {
				String check = r+alpa[i];
				if(check.contains("uud"))
				System.out.println(check);
			}
		}
	}
}
