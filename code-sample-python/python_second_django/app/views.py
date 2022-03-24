from django.http import HttpRequest, HttpResponse
from django.shortcuts import render


def hello(request: HttpRequest) -> HttpResponse:
    return HttpResponse("hello")


def index(request: HttpRequest) -> HttpResponse:
    return render(request, 'app/index.html', context=None)
