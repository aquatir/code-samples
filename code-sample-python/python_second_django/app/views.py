from django.http import HttpRequest, HttpResponse
from django.shortcuts import render

from app.forms import SearchForm


def hello(request: HttpRequest) -> HttpResponse:
    return HttpResponse("hello")


def index(request: HttpRequest) -> HttpResponse:
    return render(request, 'app/index.html', context=None)


def forms(request: HttpRequest) -> HttpResponse:
    form = SearchForm()
    return render(request, 'app/forms.html', {'form': form})
