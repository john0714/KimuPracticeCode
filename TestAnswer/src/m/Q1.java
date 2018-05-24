package m;

public class Q1 {
	public static void main(String args[] ) throws Exception {
		 String[] alpa = {"u","d","c","s","t","b"};
		 for(int i = 0 ; i < alpa.length; i++) {
		    for(int j = 0 ; j < alpa.length; j++) {
		    	  for(int k = 0 ; k < alpa.length; k++) {
		    		System.out.println(alpa[i]+alpa[j]+alpa[k]);
		    	  }
		    }
		 }
	}
}
