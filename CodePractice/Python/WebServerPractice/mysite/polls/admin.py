from django.contrib import admin

from .models import Question

admin.site.register(Question)  # Question객체가 관리 interface를 가지고 있다는것을 알려줌

