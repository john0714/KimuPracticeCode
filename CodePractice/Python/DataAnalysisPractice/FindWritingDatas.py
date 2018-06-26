import requests as rq
import operator as op
from bs4 import BeautifulSoup

"""
    Python Block Comment

    Get Data
    
    return : array{} (dict)

    jhkim 180518 
    
    input : Gallery Name, Start Page, End Page (Property : string, int, int)
    output : DataText (Property : string)
"""

class FD(object):
    def __init__(self, GName, SP, EP):  #set init
        self.GName = GName
        self.SP = int(SP)
        self.EP = int(EP)  #가져온 인수를 넣음(인스턴스의 MPcount에 직접)

    def GetData(self):

        URL = 'http://gall.dcinside.com/board/lists/?id='+self.GName+'&page='  # Gallery 주소 지정
        Pagecount = self.SP
        EndPage = self.EP  # 계산할 최대 페이지 지정
        overloadText = ""
        user_agent = 'forget'  # 사용자를 대신하여 일을 수행하는 소프트웨어 에이전트, get방식으로 값을 가져왔는데 내용이 안보이면 header에 User-Agent 아무값이나 넣어보자

        while Pagecount <= EndPage:
            response = rq.get(URL + str(Pagecount), headers={'User-Agent': user_agent})
            soup = BeautifulSoup(response.text, 'html.parser')  # 전체 HTML에서 특정 부분만 찾기 위한 html parsing
            NickNameArticle = soup.findAll('a', {'class': 'icon_pic_n'})  # 닉네임 전부 가져옴

            for index in NickNameArticle:
                overloadText = overloadText + index.text

            print("현재 작업 페이지 : " + str(Pagecount))
            Pagecount += 1

        print("페이지를 구성 중 입니다...")
        return overloadText  # return string type
