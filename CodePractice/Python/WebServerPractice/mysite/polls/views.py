from django.http import Http404, HttpResponse, HttpResponseRedirect  # http패키지의 클래스들
from django.shortcuts import get_object_or_404, render
from django.template import loader
from django.urls import reverse  # Django2.0이 되면서 django.core.urlresolvers가 django.urls로 옮겨짐
from .models import Choice, Question

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
    """
    try:
        question = Question.objects.get(pk=question_id)  # Question model에서 변수 가져옴
    except Question.DoesNotExit: # Python에선 try-catch가 아닌 try-except로 쓰는듯
        raise Http404("Question does not exist")  # 요청된 질문의 ID가 없을 경우 Http404 Exception을 발생시킴
    return render(request, 'polls/detail.html', {'question': question})
    """
    # shartcut. 객체가 존재하지 않을때 get()을 사용하여 Http404예외를 발생시키는건 자주 쓰이는 용법임.
    # 자주 쓰이므로, Django에선 이 기능에 대해 단축기능을 제공함
    question = get_object_or_404(Question, pk=question_id)  # Django모델을 첫번째 인자로 받고, 몇개의 인수를 모델 관리자의 get함수에 넘김(만약 객체가 존재하지 않을 경우, Http404예외가 발생함)
    return render(request, 'polls/detail.html', {'question': question})
    # 이외에도 리스트가 비어있을경우 Http404를 반환(위처럼 404대신에 특정 값 반환하는것도 됨)하는 get_list_or_404()메소드도 있음

def results(request, question_id):
    q = get_object_or_404(Question, pk = question_id)
    return render(request, 'polls/result.html', {'question': q})
    # detail()뷰와 거의 동일

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