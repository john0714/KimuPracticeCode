import requests as rq
import operator as op
from bs4 import BeautifulSoup

"""
    Python Block Comment

    Get Data
    
    return : array{} (dict)

    jhkim 180518
"""

class FD(object):
    def __init__(self, MPcount):  #set init
        self.MPcount = MPcount  #가져온 인수를 넣음(인스턴스의 MPcount에 직접)

    def GetData(self):
        URL = 'http://gall.dcinside.com/board/lists/?id=tree&page='  # 주소
        Pagecount = 1
        NickNamedict = {}  # dict선언(Key, Value형태)
        user_agent = 'forget'  # 사용자를 대신하여 일을 수행하는 소프트웨어 에이전트, get방식으로 값을 가져왔는데 내용이 안보이면 header에 User-Agent 아무값이나 넣어보자

        while Pagecount <= int(self.MPcount):
            response = rq.get(URL + str(Pagecount), headers={'User-Agent': user_agent})
            soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
            NickNameArticle = soup.findAll('span', {'class': 'user_nick_nm'})  # 닉네임 전부 가져옴

            for index in NickNameArticle:
                incount = True  # 이미 값이 들어가있는지 아닌지 체크(Flag)
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

            print("현재 작업 완료한 페이지 : " + str(Pagecount))
            Pagecount += 1  # pagecount++

        # NickNameList = list(set(NickNameList))  # List일 경우 중복제거
        # Sorteddict = sorted(NickNamedict.items(), key=op.itemgetter(0), reverse=True)  # dict형태의 NickNamedict를 Sort하면 list안의 tuple의 형태로 변함(순위 출력용)
        return NickNamedict  # return dict type
