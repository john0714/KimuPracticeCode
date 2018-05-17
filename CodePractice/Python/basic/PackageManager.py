#Python의 PackageManager는 pip임. 전에 잘 설치했음
#pycharm의 경우 설정의 InterPreter에서 System InterPreter의 Python3.6을 설정해서 Requests Package를 따로 설치 해줘야한다.(pip3로 다운받은 후 pycharm에서 따로 더 설정 해줘야함)
#Python의 PackageManager인 pip를 이용해서 Package를 다운받아 사용하는 예시임(Mac에선 pip3 커멘드로 사용함)
#PackageManager이용해서 다운로드 할 수도 있지만, Anaconda설치해서 Package써도 됨

import requests
from bs4 import BeautifulSoup

r = requests.get('https://codingeverybody.github.io/scraping_sample/1.html')
soup = BeautifulSoup(r.text, 'html.parser')
print('Title : '+soup.title.string)
articles = soup.findAll('div', {'class' : 'em'})
print('Article : '+articles[0].text)

print("=============================")

r = requests.get('https://codingeverybody.github.io/scraping_sample/2.html')
soup = BeautifulSoup(r.text, 'html.parser')
print('Title : '+soup.title.string)
articles = soup.findAll('div', {'class' : 'strong'})
print('Article : '+articles[0].text)

#reqeusts Package를 사용한 출력 완료
#Python 기초 강의 수강 완료