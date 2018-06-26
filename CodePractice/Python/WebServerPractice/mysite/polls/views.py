from django.http import Http404, HttpResponse, HttpResponseRedirect  # http패키지의 클래스들
from django.shortcuts import get_object_or_404, render
from django.template import loader
from django.urls import reverse  # Django2.0이 되면서 django.core.urlresolvers가 django.urls로 옮겨짐
from django.views import generic
from django.utils import timezone
from .models import Choice, Question

# 기본 Django 방식(ListView, DetailView의 2가지 Generic View를 사용하고 있음)
# 각 Generic View는 어떤 모델이 적용될 것인지 알아야함
class IndexView(generic.ListView): # generic.ListView : 개체 목록 표시 추상화
    template_name = 'polls/index.html'
    # ListView의 경의 자동 생성 된 Context변수는 question_list임. 이것을 덮어쓰기 위해 context_object_name속성을 제공하고 latest_question_list를 사용하도록 지정함
    context_object_name = 'latest_question_list'

    # 전에는 아직 게시되지않은 설문조사(미래에 pub_date가 있는 설문조사)가 표시되므로, 이걸 수정해야함
    def get_queryset(self):
        #return Question.objects.order_by('-pub_date')[:5]
        return Question.objects.filter(pub_date__lte = timezone.now()).order_by('-pub_date')[:5]

# DetailView Generic View는 URL에서 캡쳐 된 기본 값이 pk라고 기대하기 때문에 question_id를 Generic View를 Generic View를 위해 pk로 변경함
# 기본적으로 DetailView Generic View는 <app name>/<model name>_detail.html 템플릿을 사용함. 이번 예시에선 polls/question_detail.html템플릿을 사용함
class DetailView(generic.DetailView):  # generic.DetailView : 세부 정보 페이지 표시 개념 추상화
    # question 변수가 자동으로 제공되서 OK
    model = Question
    template_name = 'polls/detail.html'

    # 미래의 설문들은 목록에 나타나지 않지만, 사용자가 URL을 알고있거나, 추축하면 접근 할 수 있기에 DetailView에 비슷한 제약 조건을 추가 할 필요가 있음
    def get_queryset(self):
        return Question.objects.filter(pub_date__lte=timezone.now())

class ResultsView(generic.DetailView):  # generic.DetailView : 세부 정보 페이지 표시 개념 추상화
    model = Question
    template_name = 'polls/results.html'

# 이 아래는 기본 웹 방식(Django Framework를 쓰니까. Django방식에 맞춰서 코드 쓰도록 하자)
"""
# view화면
def index(request):
    #시스템에 저장된 최소한 5개의 투표 질문이 콤마로 분리되어 발행일에 따라 출력됨
    latest_question_list = Question.objects.order_by('-pub_date')[:5]  # 크기가 5인 배열 초기화
    template = loader.get_template('polls/index.html')
    context = {
        'latest_question_list':latest_question_list,
    }
    # output = ', '.join([q.question_text for q in latest_question_list])  # 반복문으로 출력
    # return HttpResponse(template.render(context, request))
    # shortcut
    return render(request, 'polls/index.html', context) # HttpResponse로 template에 context를 채워서 돌려주는건 자주 쓰기때문에 render라는

def detail(request, question_id):
    # shartcut. 객체가 존재하지 않을때 get()을 사용하여 Http404예외를 발생시키는건 자주 쓰이는 용법임.
    # 자주 쓰이므로, Django에선 이 기능에 대해 단축기능을 제공함
    question = get_object_or_404(Question, pk=question_id)  # Django모델을 첫번째 인자로 받고, 몇개의 인수를 모델 관리자의 get함수에 넘김(만약 객체가 존재하지 않을 경우, Http404예외가 발생함)
    return render(request, 'polls/detail.html', {'question': question})
    # 이외에도 리스트가 비어있을경우 Http404를 반환(위처럼 404대신에 특정 값 반환하는것도 됨)하는 get_list_or_404()메소드도 있음

def results(request, question_id):
    q = get_object_or_404(Question, pk = question_id)
    return render(request, 'polls/results.html', {'question': q})
    # detail()뷰와 거의 동일
"""


def vote(request, question_id):
    question = get_object_or_404(Question, pk = question_id)
    try:
        selected_choice = question.choice_set.get(pk=request.POST['choice'])  # 키로 전송된 자료에 접근할 수 있도록 해주는 사전과 같은 객체. 여기선 request.POST['choice']는 선택된 설문의 ID를 문자열로 반환함. request.POST의 값은 항상 문자열임
    except(KeyError, Choice.DoesNotExist):  # 만약 POST자료에 choice가 없으면, request.POST['choice']는 KeyError가 일어납니다. 이 코드는 KeyError를 체크하고, choice가 추어지지 않은 경우에는 에러 메세지와 함께 설문조사 폼을 다시 보여줍니다.
        return render(request, 'polls/detail.html', {
            'question':question,
            'error_message': "You didn't select a choice.",
        })
    else:
        selected_choice.votes += 1
        selected_choice.save()  # 저장해줌
        # POST데이터를 성공적으로 처리한 후에는 항상 HttpResponseRedirect를 반환해야함. 즉, 입력된 데이터로 특정 처리가 끝난 이후엔 redirect해야한다는 이야기다. 그런 의미에서 폼이라는건 controll을 하는 부분이라 볼 수 있다.
        return HttpResponseRedirect(reverse('polls:results', args=(question.id,)))
        # 이 예제에선 HttpResponseRedirect생성자 안에서 reverse()함수를 사용하고 있음. 이 함수는 뷰 함수에서 URL을 하드코딩하지 않도록 도와줌
        # 이에 따라 vote()뷰는 설문조사 결과 페이지로 리다이렉트 함(results())