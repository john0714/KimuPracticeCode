package m;
import java.util.*;

public class Q5 {
	 public static void main(String args[] ) throws Exception {
		 Scanner sc = new Scanner(System.in);
		 int N = sc.nextInt();
	        for (int i = 0; i < N; i++) {
	            String word = sc.next();
	            String lasttwocheck = word.substring(word.length()-2, word.length()); //끝부분 2개
	            if(lasttwocheck.equals("sh") || lasttwocheck.equals("ch") || lasttwocheck.equals("fe")) {
	            		if(lasttwocheck.equals("sh") || lasttwocheck.equals("ch")) {
	            			word = word + "es";
	            		} else {
	            			word = word.substring(0, word.length()-1) + "ves";
	            		}
	            } else {
	            		String check = word.substring(word.length()-1, word.length()); //끝부분 1개
		            if(check.equals("s") || check.equals("o") || check.equals("x")) {
		            		word = word + "es";
		            } else if(check.equals("f")) {
		            		word = word.substring(0, word.length()-1) + "ves";
		            } else if(check.equals("y")) {
		            		String check2 = word.substring(word.length()-2, word.length()-1);
		            		if(!check.equals("a") && !check.equals("i") && !check.equals("u") && !check.equals("e") && !check.equals("o")) {
		            			word = word.substring(0, word.length()-1) + "ies";
		            		}
		            } else {
		            		word = word + "s";
		            }
	            }
	            System.out.println(word);
	        }
	 }
}
