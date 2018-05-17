import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import requests as rq
import operator as op
from bs4 import BeautifulSoup

"""
    Python Block Comment
    
    Dcinside Plant gallery Writing Count for NickName
    
    jhkim 180517
"""

URL = 'http://gall.dcinside.com/board/lists/?id=tree&page='
pagecount = 1
NickNamedict = {}
user_agent = 'forget'  # 사용자를 대신하여 일을 수행하는 소프트웨어 에이전트, get방식으로 값을 가져왔는데 내용이 안보이면 header에 User-Agent 아무값이나 넣어보자

while pagecount <= 20:
    response = rq.get(URL+str(pagecount), headers={'User-Agent': user_agent})
    soup = BeautifulSoup(response.text, 'html.parser')  # 특정 부분만 찾기 위한 html parsing
    NickNameArticle = soup.findAll('span', {'class': 'user_nick_nm'})  # 닉네임 전부 가져옴

    for index in NickNameArticle:
        incount = True
        if NickNamedict == {}:
            NickNamedict[index.text] = 1  # dict초기화 <>[text]<> 사이 부분의 텍스트를 가져옴
        else:
            for dk in list(NickNamedict):
                if dk == index.text:
                    NickNamedict[dk] += 1  # value++
                    incount = False
                    break
            if incount:
                NickNamedict[index.text] = 1  # 위에서 헀으면 이거 안하게 만들어야함

    print("현재 완료한 페이지 : "+str(pagecount))
    pagecount+=1  #pagecount++

#NickNameList = list(set(NickNameList))  # List의 경우 중복제거
Sorteddict = sorted(NickNamedict.items(), key=op.itemgetter(1), reverse=True)
print(Sorteddict)