package getText;

import java.util.*;

public class ExpandandRemote {
	public List expand(List L, int wm, int vm) { // 단어, 가로, 세로	
		List NL = new ArrayList<String>();
		
		for(int i = 0; i < L.size(); i++) { // 총 단어
			String temp[][] = new String[7*vm][6*wm]; // [세로][가로]
			String tmp[][] = (String[][])L.get(i);

			int vposition = 0;
			for(int j = 0; j < tmp.length; j++) { // 원본세로

				int wposition = 0;
				for(int k = 0; k < tmp[j].length; k++) { // 원본가로
					for(int w = 0; w < wm; w++) { // 가로배율 횟수
						temp[vposition][w+wposition] = tmp[j][k];
					}
					wposition += wm;
				}
				for(int v = 0; v < vm; v++) { // 세로배율 횟수
					temp[v+vposition] = temp[vposition];
				}
				vposition += vm;
			}
			
			NL.add(temp);
		}
		
		return NL;
	}
	
	// 오른쪽 90도 회전
	public List rotate(List L) {
		
		List NL = new ArrayList<String>();
		
		for(int i = 0; i < L.size(); i++) { // 총 단어
			String tmp[][] = (String[][])L.get(i);
			String temp[][] = new String[tmp[0].length][tmp.length]; // [세로][가로](반대로 만듬) 

			for(int j = 0; j < tmp.length; j++) { // 원본세로
				for(int k = 0; k < tmp[j].length; k++) { // 원본가로
					temp[k][tmp.length-(j+1)] = tmp[j][k];
				}
			}
			
			NL.add(temp);
		}
		
		return NL;
	}
}
