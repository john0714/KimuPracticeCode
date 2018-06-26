from django.shortcuts import render
from django.http import HttpResponseRedirect, HttpResponse
from django.urls import reverse

# World Cloud Package
from konlpy.tag import Twitter; t = Twitter()
from wordcloud import WordCloud
from wordcloud import STOPWORDS
from PIL import Image
import jpype

# Web Page Scripting Package
from bs4 import BeautifulSoup
import requests as rq  # request

# Data Package
import numpy as np
import matplotlib.pyplot as plt
import gc

# Language Package
import nltk  # Natural Language(자연어) tool kit(도구 모음)

# Python Debugger
import pdb

# Download
import io

# Japanese
import MeCab

def index(request):
    context = {
        '': ""
    }
    return render(request, 'WCProgram/index.html', context)
    # request, template(loader), value(context = dict系)

def kr(request):
    context = {
        '': ""
    }
    return render(request, 'WCProgram/kr.html', context)

def jp(request):
    context = {
        '': ""
    }
    return render(request, 'WCProgram/jp.html', context)

def en(request):
    context = {
        '': ""
    }
    return render(request, 'WCProgram/en.html', context)

def mecab_analysis(text):
    jt = MeCab.Tagger('-Ochasen')
    jt.parse('-Ochasen')  # これ重要！！！！
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

def GetWordCloud(request):  # post처리가 일어나는곳. -> Redirct처리(CSRF보안에러 일어날수도 있음), matplotlib's Tkinter always use main thread
    check = request.POST['lang']
    URL = request.POST['address']
    user_agent = 'forget'
    overloadText = ""
    response = rq.get(URL, headers={'User-Agent': user_agent})
    soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
    mask = np.array(Image.open("WCProgram/static/WCProgram/images/oval.jpg"))  # default mask
    alldata = soup.find_all()
    taglist = set()

    # get all tags(どんなサイトでもデータを習得)
    for tag in alldata:
        taglist.add(tag.name)

    if(check == 'kr'):
        # 사용자를 대신하여 일을 수행하는 소프트웨어 에이전트, get방식으로 값을 가져왔는데 내용이 안보이면 header에 User-Agent 아무값이나 넣어보자

        Article = soup.findAll(taglist)  # multiple tag

        for index in Article:
            overloadText = overloadText + index.text + " "

        # For [No core dump will be written] Exception Clear
        if jpype.isJVMStarted():
            jpype.attachThreadToJVM()

        tokens_ko = t.nouns(overloadText)  # konlpy Package의 t를 이용하여 단어 나눔

        stop_words = ['거', '왜', '좀', '레', '뭐', '임', '코', '페', '타', '함', '요', '이', '어', '온', '내']  # Excepted Wrods
        tokens_ko = [each_word for each_word in tokens_ko if
                     each_word not in stop_words]  # for, if, not in 이용해서 제외단어 이외의 단어만 남김
        ko = nltk.Text(tokens_ko, name='갤DB')

        data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)
        tmp_data = dict(data)  # dict형식으로 데이터 변경

        # in Mac
        wc = WordCloud(font_path="/Library/Fonts/AppleGothic.ttf",
                       mask=mask, stopwords=stop_words, min_font_size=5, max_words=2000,
                       background_color="white").generate_from_frequencies(tmp_data)

        # in Ubuntu - 한글
        # wc = WordCloud(font_path="/usr/share/fonts/truetype/nanum/NanumBarunGothic.ttf",
        #               mask=mask, min_font_size=5, max_words=2000, background_color="white").generate_from_frequencies(tmp_data)

    elif(check == 'jp'):
        Article = soup.findAll(taglist)  # 닉네임 전부 가져옴

        for index in Article:
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

        # in Max
        wc = WordCloud(font_path="/Library/Fonts/Hannari.otf",
                       mask=mask, stopwords=stop_words, max_words=2000,
                       background_color='white').generate_from_frequencies(tmp_data)

        # in Ubuntu - 日本語
        # wc = WordCloud(font_path="/usr/share/fonts/truetype/fonts-japanese-gothic.ttf",
        #               mask=mask, min_font_size=5, max_words=2000, background_color="white").generate_from_frequencies(tmp_data)

    elif(check == 'en'):
        Article = soup.findAll(taglist)

        for index in Article:
            overloadText = overloadText + index.text

        text = overloadText

        stopwords = set(STOPWORDS)  # Use STOPWORDS Class
        stopwords.add("int")
        stopwords.add("ext")

        # English Don't need font check
        wc = WordCloud(mask=mask, stopwords=stopwords, max_words=2000,
                       background_color='white').generate(text)

    # killed error：ラムが足りないときによく発動
    plt.figure(figsize=(16, 16))
    plt.imshow(wc, interpolation="bilinear")
    plt.axis("off")
    #plt.savefig('GalleryDataWC.png')  # word cloud 세이브

    buf = io.BytesIO()
    plt.savefig(buf, format='png')

    del mask
    plt.clf()
    plt.close()  # If you don't close, you can see a lot of error
    response.close()

    response = HttpResponse(buf.getvalue(), content_type='image/png')  # これままでは、イメージがWeb画面に見せるだけです。
    response['Content-Disposition'] = "attachment; filename=picture.png"  # Downloadまでするコード
    #response = HttpResponseRedirect(reverse('WCProgram:index'))

    buf.close()
    Article.clear()
    gc.collect()  # garbage collect empty(for server memory)

    return response