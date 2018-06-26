import datetime  # Python의 표준 모듈인 datetime모듈.
from django.db import models
from django.utils import timezone # Django의 시간대 관련 유틸리티인 django.utils.timezone을 의미함

# 각 모델은 django.db.models.Model이라는 클래스의 서브클래스로 표현됨
# Database의 각 필드는 Field클래스의 인스턴스로서 표현됨. CharFiled는 문자필드, DateTimeField는 날짜와 시간필드를 표현
# 이것은 각 필드가 어떤 자료형을 가질수 있는지를 Django에게 말함

class Question(models.Model):  #객체
    question_text = models.CharField(max_length=200)
    pub_date = models.DateTimeField('date published')  # 미래로 pub_date를 설정하는 것은 그 시기가 되면 질문이 게시되지만 그때까지는 보이지 않는 것을 의미해야함

    def __str__(self):
        return self.question_text

    def was_published_recently(self):
        return self.pub_date >= timezone.now() - datetime.timedelta(days=1)

    def was_published_recently(self):
        now = timezone.now()
        return now - datetime.timedelta(days=1) <= self.pub_date <= now
        # 날자가 과거에 있을때만 True 반환
    #admin 화면 내용 수정
    was_published_recently.admin_order_field = 'pub_date'
    was_published_recently.boolean = True
    was_published_recently.short_description = 'Published recently?'

class Choice(models.Model):  # 객체
    question = models.ForeignKey(Question, on_delete=models.CASCADE)
    choice_text = models.CharField(max_length=200)
    votes = models.IntegerField(default = 0)

    # 모델에 __str__()메소드를 추가하는것은 객체의 표현을 대화식 프롬프트에서 편하게 보려는 이유 말고도 Django가 자동으로 생서하는 관리 사이트에서도 객체의 표현이 사용되기 때문임
    def __str__(self):
        return self.choice_text

# 이정보로 Django는, 이 app에 대하여 Database schema생성, Question과 choice객체에 접근하기 위한 Python Database접근 API 생성이 가능함
