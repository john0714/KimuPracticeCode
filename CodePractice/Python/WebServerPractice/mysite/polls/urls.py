"""mysite URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""

# view쪽의 url(URLconf 설정)
from django.urls import path
from django.conf.urls import include
from . import views

# 만약 app이 polls하나 말고 여러개가 있을경우, view에서(template를 통해)이 app을 어떻게 찾을까? 답은, app_name을 추가하여 template에서 이 공간을 지정하도록 만드는거다.
app_name = 'polls'

# views.py안의 내용을 추가(path()호출 추가)
urlpatterns = [
    # generic view형태로 변경
    path('', views.IndexView.as_view(), name='index'),
    path('<int:pk>/', views.DetailView.as_view(), name='detail'),
    path('<int:pk>/results/', views.ResultsView.as_view(), name='results'),
    path('<int:question_id>/vote/', views.vote, name='vote'),
]

"""
urlpattrerns[
# ex: /polls/
path('', views.index, name='index'), #view클래스의 index메소드를 index로 설정
# ex: /polls/[question_id]/
path('<int:question_id>/', views.detail, name='detail'),
# ex: /polls/[question_id]/results/
path('<int:question_id>/results/', views.results, name='results'),
# ex: /polls/[question_id]/vote/
path('<int:question_id>/vote/', views.vote, name='vote'),
#path('polls/latest.html', views.index), 이런식으로 view를 불러오는 코드 만들수도 있지만. 바보같아 보이지 이렇게 코드 짜진 말자
]
"""