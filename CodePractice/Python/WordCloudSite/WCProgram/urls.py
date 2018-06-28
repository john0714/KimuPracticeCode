#ここは下のURLconf

from django.urls import path

from . import views  # WCProgram's views(controller)
from . import DCdata  # WCProgram's views(controller)

from django.conf import settings
from django.conf.urls.static import static

app_name = 'WCProgram'

urlpatterns = [
    path('', views.index, name = 'index'),
    path('kr/', views.kr, name = 'kr'),  # Basic kr
    path('jp/', views.jp, name = 'jp'),  # Basic jp
    path('en/', views.en, name = 'en'),  # Basic en
    path('GetWordCloud/', views.GetWordCloud, name = 'GWC'),   # Basic Code
    path('imageset', views.upload_file, name = 'upload'),   # / 入るとイメージのロードができない。

    path('DCData/', DCdata.DCDataTemplate, name ='DCD'),   # DCdata
    path('GetDCdata/', DCdata.GetDCWordCloud, name = 'GDWC'),   # Get DC Data Code
    path('DCimageset', DCdata.upload_file, name = 'DCupload'),   # / 入るとイメージのロードができない。
]+static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)