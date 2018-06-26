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

def DCDataTemplate(request):
    context = {
        '': ""
    }
    return render(request, 'WCProgram/DCDataTemplate.html', context)

def GetDCWordCloud(request):  # post처리가 일어나는곳. -> Redirct처리(CSRF보안에러 일어날수도 있음), matplotlib's Tkinter always use main thread
    URL = request.POST['address']
    URL += request.POST['gallery'] + "&page="
    SP = int(request.POST['spage'])
    EP = int(request.POST['epage'])
    subject = request.POST['WorN']
    user_agent = 'forget'
    mask = np.array(Image.open("WCProgram/static/WCProgram/images/oval.jpg"))  # default mask

    if(subject == 'N'):
        # output Data
        overloadText = list()

        while SP <= EP:
            response = rq.get(URL + str(SP), headers={'User-Agent': user_agent})
            soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
            Article = soup.findAll('span', {'class': 'user_nick_nm'})  # 닉네임 전부 가져옴

            for index in Article:
                overloadText.append(index.text)

            print("현재 작업 페이지 : " + str(SP))
            SP += 1

        print("워드 클라우드 구현중...")

        # 닉네임의 경우 닉네임 자체를 하나의 데이터로 취급해야함 → 일반적인 한글 구분 패키지로는 안됨.
        ko = nltk.Text(overloadText, name='갤DB')
        data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)
        tmp_data = dict(data)  # dict형식으로 데이터 변경

    elif(subject == 'W'):
        # output Data
        overloadText = ""

        while SP <= EP:
            response = rq.get(URL + str(SP), headers={'User-Agent': user_agent})
            soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
            Article = soup.findAll('a', {'class': 'icon_pic_n'})  # 글 제목 데이터 전부 가져옴

            for index in Article:
                overloadText = overloadText + index.text + " "

            print("현재 작업 페이지 : " + str(SP))
            SP += 1

        # For [No core dump will be written] Exception Clear
        if jpype.isJVMStarted():
            jpype.attachThreadToJVM()

        print("워드 클라우드 구현중...")

        # 글 데이터의 경우 konlpy패키지를 써서 한글 뽑으면 됨
        tokens_ko = t.nouns(overloadText)  # konlpy Package의 t를 이용하여 단어 나눔

        stop_words = ['거', '왜', '좀', '레', '뭐', '임', '코', '페', '타', '함', '요', '이', '어', '온', '내']  # Excepted Wrods
        tokens_ko = [each_word for each_word in tokens_ko if
                     each_word not in stop_words]  # for, if, not in 이용해서 제외단어 이외의 단어만 남김
        ko = nltk.Text(tokens_ko, name='갤DB')

        data = ko.vocab().most_common(500)  # 정렬된 list-tuple형식으로 변경(Count해줌. 최대 500)
        tmp_data = dict(data)  # dict형식으로 데이터 변경

    # in Mac
    wc = WordCloud(font_path="/Library/Fonts/AppleGothic.ttf",
                   mask=mask, min_font_size=5, max_words=2000,
                   background_color="white").generate_from_frequencies(tmp_data)

    # in Ubuntu - 한글
    # wc = WordCloud(font_path="/usr/share/fonts/truetype/nanum/NanumBarunGothic.ttf",
    #               mask=mask, min_font_size=5, max_words=2000, background_color="white").generate_from_frequencies(tmp_data)

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

    response = HttpResponse(buf.getvalue(), content_type='image/png')  # 이것만 하면 이미지를 화면에 보여주는것만 함(다운로드는 직접 해야함)
    response['Content-Disposition'] = "attachment; filename=picture.png"  # Download까지 해줌
    #response = HttpResponseRedirect(reverse('WCProgram:index'))

    buf.close()
    Article.clear()
    gc.collect()  # garbage collect empty(for server memory)

    return response

