package m;

import java.util.*;

public class Q4 {
	public static void main(String args[] ) throws Exception {
		 Scanner sc = new Scanner(System.in);
		 String n = sc.next();
		 
		 Calendar cal = Calendar.getInstance();
		 
	     int year = Integer.parseInt(n.substring(0,4));
	     int month = Integer.parseInt(n.substring(5,7));
	     cal.set(Calendar.YEAR, year);
	     cal.set(Calendar.MONTH, month);
	     
	     cal.set(year, month-1, 1); // 一日にセット
	     
	     int endday = cal.getActualMaximum(Calendar.DATE);
	     int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
	     
	     for(int i = 1; i <=endday; i ++) {
	    	 	if(i==1) {
	    	 		for(int j = 1; j < dayofweek; j++) {
	    	 			System.out.print("    ");
	    	 		}
	    	 	}
	    	 	if(i<10) { //一つ文字の場合空白追加
	    	 		System.out.print(" ");
	    	 	}
	    	 	if(dayofweek%7 == 0) {
	    	 		System.out.print(" ["+i+"] ");
	    	 		System.out.println();
	    	 	} else if(dayofweek%7 == 1) {
	    	 		System.out.print(" ("+i+") ");
	    	 	} else {
	    	 		System.out.print(" "+i+" ");
	    	 	}
	    	 	dayofweek++;
	     }
	 }
}
