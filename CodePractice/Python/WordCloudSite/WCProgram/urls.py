#ここは下のURLconf

from django.urls import path

from . import views  # WCProgram's views(controller)
from . import DCdata  # WCProgram's views(controller)

app_name = 'WCProgram'

urlpatterns = [
    path('', views.index, name = 'index'),
    path('kr/', views.kr, name = 'kr'),  # Basic kr
    path('jp/', views.jp, name = 'jp'),  # Basic jp
    path('en/', views.en, name = 'en'),  # Basic en
    path('GetWordCloud/', views.GetWordCloud, name = 'GWC'),   # Basic Code

    path('DCData/', DCdata.DCDataTemplate, name ='DCD'),   # DCdata
    path('GetDCdata/', DCdata.GetDCWordCloud, name = 'GDWC'),   # Get DC Data Code
]