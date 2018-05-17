# -*- coding: utf-8 -*-
# Run : shift + F10

# 다른 언어로 작성된 프로그램API 사용하기 위한 Class
# 설치가 안된다.... pywin32가 설치가 안됨.. -> win32com은 윈도우 지향 Package라서 mac에선 사용이 안된다.
# 이쪽 공부는 여기까지. 이후 공부는 생활코딩 Python공부로 하자
import win32com.client

class CpStockCode:
    def __init__(self):
        self.stocks = {'유한양행':'A000100'} #유한양행을 받으면 A000100을 출력함
    def GetCount(self):
        return len(self.stocks)
    def NameToCode(self, name):
        return self.stocks[name]


instCpStockCode = CpStockCode() #生成者
print(instCpStockCode.GetCount())
print(instCpStockCode.NameToCode("유한양행"))

# Dispatch함수로internetExplorer를 불러서 출력함
explore = win32com.client.Dispatch("internetExplorer.Application")
explore.Visible = True