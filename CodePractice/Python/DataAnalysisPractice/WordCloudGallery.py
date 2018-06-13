import numpy as np
import requests as rq
import matplotlib.pyplot as plt
import random
import platform
import nltk  # Natural Language(자연어) tool kit(도구 모음)
import FindWritingDatas
from PIL import Image
from matplotlib import font_manager, rc
from wordcloud import WordCloud   # wordcloud Package안의 WordCloud Class사용
from wordcloud import STOPWORDS
from konlpy.tag import Twitter; t = Twitter()  # 형태소 분석 엔진 Twitter사용
from bs4 import BeautifulSoup


# matplotlib의 한글 폰트 설정(OS별)
if platform.system() == 'Darwin':  # Mac의 경우
    rc('font', family='AppleGothic')
elif platform.system() == 'Windows':
    font_name = font_manager.FontProperties(fname="c:/Windows/Fonts/malgun.ttf")
    rc('font', family=font_name)
else:
    print('Unknown system... sorry')

# grey 색 설정
def grey_color_func(word, font_size, position, orientation, random_state=None, **kwargs):
    return "hsl(0, 0%%, %d%%)" % random.randint(60, 100)

"""
# Gallery Writing NickName word cloud
# 키(닉네임)를 Word Cloud로 출력, 값(숫자)을 Word Cloud형태로 만듬
# FindDatas.py에서 데이터 카운트 해서 이미 닉네임의 dict데이터를 출력한걸 이용
MaxPage = input("1페이지부터 찾을 페이지 수 : ")  # Input MaxPage Data

FindData = FindNickNameDatas.FD(MaxPage)  # Export Array(use requests)
Datas = FindData.InsertData()  # 함수를 사용해서 dict데이터를 가져옴(정렬 없이, 중복체크만 한 dict형태)
print(Datas)

mask = np.array(Image.open("WordCloudData/oval.jpg"))

WC = WordCloud(font_path='/Library/Fonts/AppleGothic.ttf',  # Max 한글용 폰트 -> 한글 출력을 위해 설정 필요
               mask=mask, min_font_size=10, max_words=2000, background_color="white"
               ).generate_from_frequencies(Datas)  # dict 형태의 파일에서 불러옴

plt.figure(figsize=(6,6))  # (가로, 세로)이미지 해상도에 맞춰서 수정(다르면 해상도 이상하게 나옴), 화면해상도보다 크면 이미지가 짤려서 적힐수도 있음
plt.imshow(WC, interpolation="bilinear")
plt.axis("off")
plt.show()
"""

# Idol Master Gallery word Cloud
# word cloud package를 이용, @갤 글 제목 전부 뽑아와서 단어별로 나눈 후 타원 형태로 출력
print("갤러리 제목 데이터 출력 프로그램 \n\n"
      "갤러리 목록 \n"
      "1:식물 갤러리, 2:아이돌 마스터 갤러리 \n"
      "\n")


GalleryList = {1:"tree", 2:"idolmaster"}

GName = input("갤러리를 선택 해주세요 : ")
GName = GalleryList[int(GName)]

StartPage = input("시작 페이지를 입력 해주세요 : ")
EndPage = input("끝 페이지를 입력 해주세요 : ")

FD = FindWritingDatas.FD(GName, StartPage, EndPage)
overloadText = FD.GetData()

tokens_ko = t.nouns(overloadText)  # konlpy Package의 t를 이용하여 단어 나눔
stop_words = ['거','왜','좀','레','뭐','임','코','페','타','함']  # 제외 단어
tokens_ko = [each_word for each_word in tokens_ko if each_word not in stop_words]  # for, if, not in 이용해서 제외단어 이외의 단어만 남김
ko = nltk.Text(tokens_ko, name='@갤DB')
data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)
tmp_data = dict(data)  # dict형식으로 데이터 변경

mask = np.array(Image.open("WordCloudData/oval.jpg"))

wc = WordCloud(font_path="/Library/Fonts/AppleGothic.ttf",
               mask = mask, min_font_size=5, max_words=2000, background_color="white").generate_from_frequencies(tmp_data)

plt.figure(figsize=(16,16))
plt.imshow(wc, interpolation="bilinear")
plt.axis("off")
plt.savefig('GallreyDataWC.png')  # word cloud 세이브
plt.show()  # 세이브 이후 보여줌. 화질은 기존의 이미지 화질 그대로