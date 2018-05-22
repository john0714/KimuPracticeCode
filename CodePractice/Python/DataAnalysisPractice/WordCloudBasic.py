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

# Use Korean
ko_con_text = kolaw.open('constitution.txt').read()  # 출력 연습용 대한민국 헌법
tokens_ko = t.nouns(ko_con_text)  # 텍스트를 단어별로 나눔(한글)
stop_words = ['제','월','일','조','수','때','그','이','바','및','안']  # 금지 단어 수동 설정(완전히 같은 텍스트 출력하지 않음)
tokens_ko = [each_word for each_word in tokens_ko if each_word not in stop_words]  # stopword 적용
ko = nltk.Text(tokens_ko, name='대한민국 헌법')

data = ko.vocab().most_common(500)  # 카운트 해서 정렬까지 함
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