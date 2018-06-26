package getText;

import java.io.*;
import java.util.*;

//import getText.*; //static형태로 쓸꺼라면 사용

public class MainClass {
	public static void main(String ars[]) {
		// 텍스트를 비트맵으로 전환
		Getbitword GBW = new Getbitword();
		ExpandandRemote EAR = new ExpandandRemote();
		
		Scanner s = new Scanner(System.in);
		System.out.println("영어 메세지를 입력하세요");
		List result = GBW.getbitword(s.nextLine()); 
		
		int wm = 2; // 가로 2배
		int vm = 6; // 세로 6배
		result = EAR.expand(result, wm, vm); // 확대
		result = EAR.rotate(result); // 오른쪽 90도 회전
		
		File file = new File("answer.txt");

		if(file.exists()) {
			file.delete();
		}
		
		// 출력
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
			for(int i = 0; i < result.size(); i++) {
				String tmp[][] = (String[][])result.get(i);
				for(int j = 0; j < tmp.length; j++) {
					for(int k = 0; k < tmp[j].length; k++) { // 원본가로
						fw.write(tmp[j][k]);
						fw.flush(); // 반드시 텍스트를 쓰게 하기 위해 필요함(버퍼 출력)
					}
					fw.write("\n");
				}
				fw.write("\n");
			}
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("출력 완료!(위치:프로젝트 폴더 안)");
	}
}
