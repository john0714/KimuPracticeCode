import numpy as np
import requests as rq
import matplotlib.pyplot as plt
import random
import platform
import FindDatas
import MeCab
from PIL import Image
from matplotlib import font_manager, rc
from wordcloud import WordCloud   # wordcloud Package안의 WordCloud Class사용
from wordcloud import STOPWORDS
from wordcloud import ImageColorGenerator
import nltk  # Natural Language(자연어) tool kit(도구 모음)
from konlpy.corpus import kolaw  # 한국어 사용을 위한 Package
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
# Square Practice
text = open('WordCloudData/constitution.txt').read()
wordcloud = WordCloud(max_font_size=40).generate(text)

plt.figure(figsize=(12,12))  # 사이즈 지정
plt.imshow(wordcloud, interpolation='bilinear')  # wordcloud show
plt.axis("off")
plt.show()
"""

"""
# alice Practice
text = open('WordCloudData/alice.txt').read()
alice_mask = np.array(Image.open("WordCloudData/alice_mask.png"))  # 이미지를 array형태로 가져옴  -> 픽셀별 색이 표시됨

stopwords = set(STOPWORDS)
stopwords.add("said")

wordcloud = WordCloud(background_color="white", max_words=2000, mask=alice_mask,
                      stopwords=stopwords)

wc = wordcloud.generate(text)

plt.figure(figsize=(8,8))
plt.imshow(wc.recolor(color_func=grey_color_func, random_state=3), interpolation='bilinear')
plt.axis("off")
plt.show()
"""

"""
# startwars Pratice
text = open("WordCloudData/a_new_hope.txt").read()

text = text.replace("HAN", "Han")
text = text.replace("LUKE'S", "Luke")

mask = np.array(Image.open("WordCloudData/stormtrooper_mask.png"))

stopwords = set(STOPWORDS)
stopwords.add("int")
stopwords.add("ext")

wc = WordCloud(max_words=1000, mask=mask, stopwords=stopwords, margin=10,
               random_state=1).generate(text)
# store default colored image
default_colors = wc.to_array()

plt.figure(figsize=(8,8))
plt.title("Custom colors")
plt.imshow(wc.recolor(color_func=grey_color_func, random_state=3), interpolation="bilinear")
plt.axis("off")
plt.show()
"""

"""
# alice Practice2
text = open('WordCloudData/alice.txt').read()
# read the mask / color image taken from
alice_coloring = np.array(Image.open("WordCloudData/alice_color.png"))
#alice_mask = np.array(Image.open("WordCloudData/alice_mask.png"))  # 이미지를 array형태로 가져옴  -> 픽셀별 색이 표시됨

stopwords = set(STOPWORDS)
stopwords.add("said")

wordcloud = WordCloud(background_color="white", max_words=2000, mask=alice_coloring,
                      stopwords=stopwords, max_font_size=40, random_state=42)

# generate word cloud
wc = wordcloud.generate(text)
image_colors = ImageColorGenerator(alice_coloring)  # image_colors 반영

plt.figure(figsize=(8,8))
plt.imshow(wc.recolor(color_func=image_colors), interpolation='bilinear')
plt.axis("off")
plt.show()
"""

"""
# Use Korean
ko_con_text = kolaw.open('constitution.txt').read()  # 출력 연습용 대한민국 헌법
tokens_ko = t.nouns(ko_con_text)
stop_words = ['제','월','일','조','수','때','그','이','바','및','안']  # 금지 단어 수동 설정(완전히 같은 텍스트 출력하지 않음)
tokens_ko = [each_word for each_word in tokens_ko if each_word not in stop_words]  # stopword 적용
ko = nltk.Text(tokens_ko, name='대한민국 헌법')

data = ko.vocab().most_common(500)
tmp_data = dict(data)

korea_coloring = np.array(Image.open("WordCloudData/korea_mask.jpg"))
image_colors = ImageColorGenerator(korea_coloring)

wordcloud = WordCloud(font_path='/Library/Fonts/AppleGothic.ttf',  # Max 한글용 폰트
                      relative_scaling=0.2, mask=korea_coloring,
                      background_color='white',
                      min_font_size=1, max_font_size=40  # 최대, 최소 폰트 사이즈 지정
                      ).generate_from_frequencies(tmp_data)

plt.figure(figsize=(12,12))
plt.imshow(wordcloud.recolor(color_func=image_colors), interpolation="bilinear")
plt.axis("off")
plt.show()
"""

"""
# SlamDunk
SDtext = open('WordCloudData/slamdunk.txt').read()  # 텍스트 전체 불러옴
tokens_ko = t.nouns(SDtext)  # konlpy Package의 t를 이용하여 단어 나눔
stop_words = ['팬','파','교','명','책','씨','년','전','친','고']  # 제외 단어
tokens_ko = [each_word for each_word in tokens_ko if each_word not in stop_words]  # for, if, not in 이용해서 제외단어 이외의 단어만 남김
ko = nltk.Text(tokens_ko, name='슬램덩크')
data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식
tmp_data = dict(data)  # dict형식으로 데이터 변경
print(data)

slamdunk_coloring = np.array(Image.open("WordCloudData/slamdunk.jpg"))
image_colors = ImageColorGenerator(slamdunk_coloring)

wordcloud = WordCloud(font_path='/Library/Fonts/AppleGothic.ttf',  # Max 한글용 폰트 -> 한글 출력을 위해 설정 필요
                      relative_scaling=0.2, mask=slamdunk_coloring,
                      background_color='white',  # 배경 색 지정
                      min_font_size=1, max_font_size=40  # 최대, 최소 폰트 사이즈 지정
                      ).generate_from_frequencies(tmp_data)  # wordcloud의 데이터 지정

plt.figure(figsize=(8,8))
plt.imshow(wordcloud.recolor(color_func=image_colors), interpolation="bilinear")  # 컬러 이미지 색 지정
plt.axis("off")
plt.show()
"""

"""
# Gallery Writing NickName word cloud
# 키(닉네임)를 Word Cloud로 출력, 값(숫자)을 Word Cloud형태로 만듬
# 이번엔 FindDatas.py에서 데이터 카운트 해서 이미 dict를 출력한걸 이용 했지만,  word cloud에서 그냥 list데이터를 카운트해서 바꿔주는 함수도 있음
MaxPage = input("1페이지부터 찾을 페이지 수 : ")  # Input MaxPage Data

FindData = FindDatas.FD(MaxPage)  # Export Array(use requests)
Datas = FindData.InsertData()  # 함수를 사용해서 dict데이터를 가져옴(key, value)
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
# word cloud package를 이용, @갤 글 제목 전부 뽑아와서 단어별로 나눈 후 타원 형태로 출력해보자
URL = 'http://gall.dcinside.com/board/lists/?id=idolmaster&page='  # 주소
Pagecount = 1
MaxPage = 120  # input안넣고 그냥 120페이지까지 계산
overloadText = ""
user_agent = 'forget'  # 사용자를 대신하여 일을 수행하는 소프트웨어 에이전트, get방식으로 값을 가져왔는데 내용이 안보이면 header에 User-Agent 아무값이나 넣어보자

while Pagecount <= int(MaxPage):
    response = rq.get(URL + str(Pagecount), headers={'User-Agent': user_agent})
    soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
    NickNameArticle = soup.findAll('a', {'class': 'icon_pic_n'})  # 닉네임 전부 가져옴
    for index in NickNameArticle:
        overloadText = overloadText+index.text

    print("현재 작업 페이지 : " + str(Pagecount))
    Pagecount+=1

tokens_ko = t.nouns(overloadText)  # konlpy Package의 t를 이용하여 단어 나눔
stop_words = ['거','왜','좀','레','뭐','임','코','페','타','함']  # 제외 단어
tokens_ko = [each_word for each_word in tokens_ko if each_word not in stop_words]  # for, if, not in 이용해서 제외단어 이외의 단어만 남김
ko = nltk.Text(tokens_ko, name='@갤DB')
data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)
tmp_data = dict(data)  # dict형식으로 데이터 변경

mask = np.array(Image.open("WordCloudData/oval.jpg"))

wc = WordCloud(font_path="/Library/Fonts/AppleGothic.ttf",
               mask = mask, min_font_size=5, max_words=2000, background_color="white").generate_from_frequencies(tmp_data)

plt.figure(figsize=(8,8))
plt.imshow(wc, interpolation="bilinear")
plt.axis("off")
plt.show()

"""
# 杏
def mecab_analysis(text):
    jt = MeCab.Tagger('-Ochasen')
    node = jt.parseToNode(text)
    output = []
    while(node):
        if node.surface != "":  # ヘッダとフッタを除外
            word_type = node.feature.split(",")[0]
            if word_type in ["形容詞", "動詞","名詞", "副詞"]:
                output.append(node.surface)
        node = node.next
        if node is None:
            break
    return output

URL = 'http://dic.nicovideo.jp/a/%E5%8F%8C%E8%91%89%E6%9D%8F'  # 주소
user_agent = "anzu"
overloadText = ""
response = rq.get(URL, headers={'User-Agent': user_agent})
soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
anzuArticle = soup.findAll('p')  # 닉네임 전부 가져옴

for index in anzuArticle:
    overloadText = overloadText+index.text

tokens_jp = mecab_analysis(overloadText)  # konlpy Package의 t를 이용하여 단어 나눔, "形容詞", "動詞","名詞", "副詞"만 필요
ko = nltk.Text(tokens_jp, name='杏')
data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)
tmp_data = dict(data)  # dict형식으로 데이터 변경

stop_words = [ u'てる', u'いる', u'なる', u'れる', u'する', u'ある', u'こと', u'これ', u'さん', u'して', \
             u'くれる', u'やる', u'くださる', u'そう', u'せる', u'した',  u'思う',  \
             u'それ', u'ここ', u'ちゃん', u'くん', u'', u'て',u'に',u'を',u'は',u'の', u'が', u'と', u'た', u'し', u'で', \
             u'ない', u'も', u'な', u'い', u'か', u'ので', u'よう', u'']

anzu_coloring = np.array(Image.open("WordCloudData/anzu.jpg"))

wc = WordCloud(font_path="/Library/Fonts/Hannari.otf",
               max_words=2000, mask=anzu_coloring, stopwords=stop_words,
               max_font_size=40).generate_from_frequencies(tmp_data)

image_colors = ImageColorGenerator(anzu_coloring)  # image_colors 반영

plt.figure(figsize=(5,4))
plt.imshow(wc.recolor(color_func=image_colors), interpolation='bilinear')
plt.axis("off")
plt.show()
"""