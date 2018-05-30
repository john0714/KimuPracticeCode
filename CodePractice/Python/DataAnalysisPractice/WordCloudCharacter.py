import numpy as np
import matplotlib.pyplot as plt
import requests as rq
import platform
import MeCab
import nltk
from PIL import Image
from matplotlib import font_manager, rc
from wordcloud import WordCloud  # wordcloud Package안의 WordCloud Class사용
from wordcloud import STOPWORDS
from wordcloud import ImageColorGenerator
from bs4 import BeautifulSoup

# matplotlib의 한글 폰트 설정(OS별)
if platform.system() == 'Darwin':  # Mac의 경우
    rc('font', family='AppleGothic')
elif platform.system() == 'Windows':
    font_name = font_manager.FontProperties(fname="c:/Windows/Fonts/malgun.ttf")
    rc('font', family=font_name)
else:
    print('Unknown system... sorry')



def mecab_analysis(text):
    jt = MeCab.Tagger('-Ochasen')
    node = jt.parseToNode(text)
    output = []
    while (node):
        if node.surface != "":  # ヘッダとフッタを除外
            word_type = node.feature.split(",")[0]
            if word_type in ["形容詞", "動詞", "名詞", "副詞"]:
                output.append(node.surface)
        node = node.next
        if node is None:
            break
    return output

# 杏
URL = 'http://dic.nicovideo.jp/a/%E5%8F%8C%E8%91%89%E6%9D%8F'  # 주소
user_agent = "anzu"
overloadText = ""
response = rq.get(URL, headers={'User-Agent': user_agent})
soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
anzuArticle = soup.findAll('p')  # 닉네임 전부 가져옴

for index in anzuArticle:
    overloadText = overloadText + index.text

tokens_jp = mecab_analysis(overloadText)  # konlpy Package의 t를 이용하여 단어 나눔, "形容詞", "動詞","名詞", "副詞"만 필요
jp = nltk.Text(tokens_jp, name='杏')  # 중복 제거를 위해 token형식으로 만듬
data = jp.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)(
tmp_data = dict(data)  # dict형식으로 데이터 변경

stop_words = ['てる', 'いる', 'なる', 'れる', 'する', 'ある', 'こと', 'これ', 'さん', 'して',
              'くれる', 'やる', 'くださる', 'そう', 'せる', 'した', '思う',
              'それ', 'ここ', 'ちゃん', 'くん', '', 'て', 'に', 'を', 'は', 'の', 'が', 'と', 'た', 'し', 'で',
              'ない', 'も', 'な', 'い', 'か', 'ので', 'よう', '[', ']', '/']
# Python 3.0부턴 유니코드 표현을 위해 앞에 u를 붙일 필요가 없음(이미 유니코드로 표현되므로)

anzu_coloring = np.array(Image.open("WordCloudData/anzu.jpg"))

wc = WordCloud(font_path="/Library/Fonts/Hannari.otf",
               max_words=2000, mask=anzu_coloring, stopwords=stop_words, background_color='gray'
               ).generate_from_frequencies(tmp_data)

image_colors = ImageColorGenerator(anzu_coloring)  # image_colors 반영

plt.figure(figsize=(5, 5))
plt.imshow(wc.recolor(color_func=image_colors), interpolation='bilinear')
plt.axis("off")
plt.savefig('anzuwc.jpg')
plt.show()

"""
# エスティニアン, RedWarrior
text = open('WordCloudData/estiniann.txt').read()  # 출력 연습용 대한민국 헌법
a_coloring = np.array(Image.open("WordCloudData/redwarrior.jpg"))

wordcloud = WordCloud(background_color="white", max_words=2000, mask=a_coloring)

# generate word cloud
wc = wordcloud.generate(text)
image_colors = ImageColorGenerator(a_coloring)  # image_colors 반영

plt.figure(figsize=(20,15))
plt.imshow(wc.recolor(color_func=image_colors), interpolation='bilinear')
plt.axis("off")  # 그래프 눈금표 안보이도록 제거
plt.savefig('FF14.png')  # 파일 세이브
plt.show()
"""

"""
# ふれ
text = open('WordCloudData/a_new_hope.txt').read()  # 출력 연습용 대한민국 헌법
a_coloring = np.array(Image.open("WordCloudData/hure.jpg"))

wordcloud = WordCloud(background_color="white", max_words=2000, mask=a_coloring)

# generate word cloud
wc = wordcloud.generate(text)
image_colors = ImageColorGenerator(a_coloring)  # image_colors 반영

plt.figure(figsize=(20,15))
plt.imshow(wc.recolor(color_func=image_colors), interpolation='bilinear')
plt.axis("off")  # 그래프 눈금표 안보이도록 제거
plt.savefig('hure.png')  # 파일 세이브
plt.show()
"""