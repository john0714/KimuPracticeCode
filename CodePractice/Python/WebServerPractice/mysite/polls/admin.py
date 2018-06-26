from django.contrib import admin
from django.contrib.admin import AdminSite
from django.utils.translation import ugettext_lazy
from .models import Choice, Question

# 관리 폼 위치 및 내용 변경
class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 3

class QuestionAdmin(admin.ModelAdmin):
    fieldsets = [
        (None,               {'fields': ['question_text']}),
        ('Date information', {'fields': ['pub_date'], 'classes':['collapse']}),
    ]
    inlines = [ChoiceInline]
    list_display = ('question_text', 'pub_date', 'was_published_recently')
    list_filter = ['pub_date']  # 필터 추가
    search_fields = ['question_text']

admin.site.register(Question, QuestionAdmin) # Question객체가 관리 interface를 가지고 있다는것을 알려줌

# 비 효율적인 방법
#admin.site.register(Choice)

admin.site.site_header = '내 사이트 관리자 페이지'