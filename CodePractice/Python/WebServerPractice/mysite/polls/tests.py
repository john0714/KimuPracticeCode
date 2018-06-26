import datetime

from django.test import TestCase
from django.utils import timezone
from django.urls import reverse

from .models import Question

# pub_date를 가진  Question인스턴스를 생성하는 메소드를 가진 django.test.TestCase의 서브클래스를 생성함
class QuestionModelTests(TestCase):
    def test_was_published_recently_with_future_question(self):
        """
        was_published_recently() returns False for questions whose pub_date is older than 1 day.
        """
        time = timezone.now() + datetime.timedelta(days=30)
        future_question = Question(pub_date=time)  # models의 Question클래스의 pub_date에서 값을 가져와서 테스트함
        self.assertIs(future_question.was_published_recently(), False)

    def test_was_published_recently_with_recent_question(self):
        """
        was_published_recently() returns True for questions whose pub_date is within the last day.
        """
        time = timezone.now() - datetime.timedelta(hours=23, minutes=59, seconds=59)
        recent_question = Question(pub_date=time)
        self.assertIs(recent_question.was_published_recently(), True)

# 위 테스트 내용에선 False를 반환받기 원한다는 내용이 들어감.
# 실제로 테스트 실행해보면 False를 반환받기 원하지만 True가 반환되어서 FAILED가 뜬걸 볼 수 있음
# Question.was_published_recently()는 pub_date가 미래에 있다면 False를 반환해야함(models.py에서 수정 가능)

# 새로운 테스트 클래스와 함께 질문들을 생성하는 함수를 만듬
# 질문 생성함수인 create_question은 설줌능ㄹ 생성하는 부분에서 반복 사용
def create_question(question_text, days):
    time = timezone.now() + datetime.timedelta(days=days)
    return Question.objects.create(question_text = question_text, pub_date = time)

# 사이트 관리자 입력 및 사용자 경험에 대한 이야기를 하는 테스트를 만들었고, 모든 상태와 시스템 상태의 모든 새로운 변경사항에 대해 예상하는 결과가 출력되는지 확인함
class QuestionIndexViewTests(TestCase):
    def test_no_question(self):  # 질문을 생성하지는 않지만 "poll이 없습니다"라는 메세지를 확인함
        response = self.client.get(reverse('polls:index'))
        self.assertEqual(response.status_code, 200)
        self.assertContains(response, "No polls are available.")
        self.assertQuerysetEqual(response.context['latest_question_list'], [])

    def test_past_question(self):  # 질문을 생성하고 그 질문이 리스트에 나타나는지 확인함
        create_question(question_text="Past question.", days=-30)
        response = self.client.get(reverse('polls:index'))
        self.assertQuerysetEqual(
            response.context['latest_question_list'],
            ['<Question: Past question.>']
        )

    def test_future_question(self):  # 나중에 pub_date로 질문을 만듬. 데이터베이스는 각 테스트 메소드마다 재설정 되므로, 첫번째 질문은 더이상 존재하지 않으므로 다시 인덱스에 질문이 없어야함
        create_question(question_text="Future question.", days=30)
        response = self.client.get(reverse('polls:index'))
        self.assertContains(response, "No polls are available.")
        self.assertQuerysetEqual(response.context['latest_question_list'], [])

    def test_future_questionm_and_pas_question(self):
        create_question(question_text="Past question.", days=-30)
        create_question(question_text="Future question.", days=30)
        response = self.client.get(reverse('polls:index'))
        self.assertQuerysetEqual(
            response.context['latest_question_list'],
            ['<Question: Past question.>']
        )

    def test_two_past_question(self):
        create_question(question_text="Past question 1.", days=-30)
        create_question(question_text="Past question 2.", days=-5)
        response = self.client.get(reverse('polls:index'))
        self.assertQuerysetEqual(
            response.context['latest_question_list'],
            ['<Question: Past question 2.>', '<Question: Past question 1.>']
        )

class QuestionDetailViewTests(TestCase):
    def test_future_question(self):
        future_question = create_question(question_text='Future question.', days=5)
        url = reverse('polls:detail', args=(future_question.id,))
        response = self.client.get(url)
        self.assertEqual(response.status_code, 404)

    def test_past_question(self):
        past_question = create_question(question_text='Past question.', days=-5)
        url = reverse('polls:detail', args=(past_question.id,))
        response = self.client.get(url)
        self.assertContains(response, past_question.question_text)