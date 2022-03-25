from django.http import HttpRequest, HttpResponse, Http404
from django.shortcuts import render, get_object_or_404
from django.template import loader

from polls.models import Question


def index(request: HttpRequest) -> HttpResponse:
    latest_question_list = Question.objects.order_by('-pub_date')[:5]
    context = {
        'latest_question_list': latest_question_list,
    }

    # You can either use a full form or short 'render' function form
    # template = loader.get_template('polls/index.html')
    # return HttpResponse(template.render(context, request))

    return render(request, 'polls/index.html', context)


def detail(request: HttpRequest, question_id: int) -> HttpResponse:

    # You can either use a full form of short 'get_object_or_404' to get table entry by pk
    # try:
    #     question = Question.objects.get(pk=question_id)
    # except Question.DoesNotExist:
    #     raise Http404("Question does not exist")

    question = get_object_or_404(Question, pk=question_id)
    return render(request, 'polls/detail.html', {'question': question})


def results(request: HttpRequest, question_id: int) -> HttpResponse:
    response = "You're looking at the results of question %s."
    return HttpResponse(response % question_id)


def vote(request: HttpRequest, question_id: int) -> HttpResponse:
    return HttpResponse("You're voting on question %s." % question_id)
