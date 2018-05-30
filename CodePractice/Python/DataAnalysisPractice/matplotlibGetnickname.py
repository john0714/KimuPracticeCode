import pandas as pd
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt

import FindNickNameDatas


# /Library/Fonts에 폰트 다운받고 User/user01/FontList.json에 직접 경로 설정해서 폰트 추가함
from matplotlib import font_manager, rc
font_name = font_manager.FontProperties(fname="/Library/Fonts/NanumBarunGothic.otf").get_name()
rc('font', family=font_name)  #한글, 일어 사용설정
mpl.rcParams.update({'font.size': 7}) # 폰트 사이즈 변경

"""
    Python Block Comment
    
    Dcinside Plant gallery Writing Count for NickName
    
    jhkim 180517
"""

MaxPage = input("1페이지부터 찾을 페이지 수 : ")  # Input Data

FindData = FindNickNameDatas.FD(MaxPage)  # Export Array(use requests)
Datas = FindData.GetData()  # 함수를 사용해서 dict데이터를 가져옴
Dindex = []
print(Datas)  # 정렬 없이, 중복체크만 한 dict형태

Dataspd = pd.Series(Datas)

print(Dataspd.index)  # 닉네임들
print(Dataspd.values)  # 값들
print(Dataspd)

#plt.figure()  # 일반적인 선형 그래프
#Dataspd.plot()
plt.title("식물갤 글 수 카운트")
#plt.legend(loc='best')
#plt.bar(Dataspd.index, Dataspd.values)  #수직 막대 그래프
plt.barh(Dataspd.index, Dataspd.values)  #수평 막대 그래프
#plt.pie(Dataspd)  # 원형 그래프
plt.show()  # 그래프를 보여줌