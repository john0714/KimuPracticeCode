package question;
//영문 이름 점수 합계 구하기(5개 이상의 알파벳 이름)

import java.io.*;
import java.util.*;

public class Problem22 {
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("data/names.txt"));
			List words = getwords(in); // init
			Collections.sort(words); // 1. 정렬(알파벳 순)
			long result = 0;
			
			for(int i = 0; i < words.size(); i++) { // 0~5162 단어 순서대로 부름
				int alpa = 0;
				String word = (String)words.get(i);
				for(int l = 0; l < word.length(); l++) { // 단어의 알파벳 하나
					alpa += (int)word.substring(l, l+1).charAt(0)-64; // 숫자로 치환해서 더함
				}
				result += alpa * (i+1); //모두 더하기
			}
			
			System.out.println(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		long end = System.currentTimeMillis();
		System.out.println("프로그램이 종료 될 때 까지 걸린 시간 : " + (end-start)/1000.0 + "초");
	}
	
	// Get words in TextFile
	public static List getwords(BufferedReader in) {
		String s = "";
		int single = 0;
		boolean check = false;
		List words = new ArrayList<String>();
		
		try {
			while((single = in.read()) != -1) {
				if((char)single == '\"') {  // by using double quotes you create String constant (" "), while with single quotes it's a char constant (' ').
					check ^= true;
					continue;
				}
				
				if(check) {
					s += (char)single;
					continue;
				} else {
					words.add(s);
					s = "";
				}
			}
			words.add(s); // Add last Word ALONSO
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return words;
	}
}
