package getText;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Getbitword {
	public List getbitword(String s) {	
		List L = new ArrayList<String>();
		
		for(int i = 0; i < s.length(); i++) {
			if(s.substring(i, i+1).equals("A")) {
				L.add(inputalp(1, 1+7));
			} else if(s.substring(i, i+1).equals("B")) {
				L.add(inputalp(9, 9+7));
			} else if(s.substring(i, i+1).equals("C")) {
				L.add(inputalp(17, 17+7));
			} else if(s.substring(i, i+1).equals("D")) {
				L.add(inputalp(25, 25+7));
			} else if(s.substring(i, i+1).equals("E")) {
				L.add(inputalp(33, 33+7));
			} else if(s.substring(i, i+1).equals("F")) {
				L.add(inputalp(41, 41+7));
			} else if(s.substring(i, i+1).equals("G")) {
				L.add(inputalp(49, 49+7));
			} else if(s.substring(i, i+1).equals("H")) {
				L.add(inputalp(57, 57+7));
			} else if(s.substring(i, i+1).equals("I")) {
				L.add(inputalp(65, 65+7));
			} else if(s.substring(i, i+1).equals("J")) {
				L.add(inputalp(73, 73+7));
			} else if(s.substring(i, i+1).equals("K")) {
				L.add(inputalp(81, 81+7));
			} else if(s.substring(i, i+1).equals("L")) {
				L.add(inputalp(89, 89+7));
			} else if(s.substring(i, i+1).equals("M")) {
				L.add(inputalp(97, 97+7));
			} else if(s.substring(i, i+1).equals("N")) {
				L.add(inputalp(105, 105+7));
			} else if(s.substring(i, i+1).equals("O")) {
				L.add(inputalp(113, 113+7));
			} else if(s.substring(i, i+1).equals("P")) {
				L.add(inputalp(121, 121+7));
			} else if(s.substring(i, i+1).equals("Q")) {
				L.add(inputalp(129, 129+7));
			} else if(s.substring(i, i+1).equals("R")) {
				L.add(inputalp(137, 137+7));
			} else if(s.substring(i, i+1).equals("S")) {
				L.add(inputalp(145, 145+7));
			} else if(s.substring(i, i+1).equals("T")) {
				L.add(inputalp(153, 153+7));
			} else if(s.substring(i, i+1).equals("U")) {
				L.add(inputalp(161, 161+7));
			} else if(s.substring(i, i+1).equals("V")) {
				L.add(inputalp(169, 169+7));
			} else if(s.substring(i, i+1).equals("W")) {
				L.add(inputalp(177, 177+7));
			} else if(s.substring(i, i+1).equals("X")) {
				L.add(inputalp(185, 185+7));
			} else if(s.substring(i, i+1).equals("Y")) {
				L.add(inputalp(193, 193+7));
			} else if(s.substring(i, i+1).equals("Z")) {
				L.add(inputalp(201, 201+7));
			} else { // " "
				L.add(getvoid());
			}
		}
		
		return L;
	}
	
	// 0. 폰트 데이터
	public static String[][] inputalp(int s, int e){
		String temp[][] = new String[7][6]; // [세로][가로]
		int w = 0;
		
		try {
			while(s < e) {
				//Java8이상(java.nio.file)
				String SLine = Files.readAllLines(Paths.get("data/bitmap.txt")).get(s);
				
				for(int v = 0; v < SLine.length(); v++) {
					if(String.valueOf(SLine.substring(v, v+1)).equals("1")) {
						temp[w][v] = "@";
					} else {
						temp[w][v] = " ";
					}
				}
				w++;
				s++;
			}
			
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	
		return temp;
	}
	
	// 공백
	public static String[][] getvoid() {
		String temp[][] = new String [7][6];
		
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[i].length; j++) {
				temp[i][j] = " ";
			}
		}
		
		return temp;
	}
}
