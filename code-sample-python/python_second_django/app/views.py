from django.core.exceptions import ValidationError
from django.http import HttpRequest, HttpResponse
from django.shortcuts import render
from django.utils.translation import gettext

from app.forms import TestForm


def hello(request: HttpRequest) -> HttpResponse:
    return HttpResponse("hello")


def index(request: HttpRequest) -> HttpResponse:
    return render(request, 'app/index.html', context=None)


def forms(request):

    # Use default values for the form. Could also do in on forms.py level
    initial_dict = {
        "text": "Some initial data",
        "integer": 123,
    }
    form = TestForm(request.POST or None, initial=initial_dict)
    data = "None"
    text = "None"
    if form.is_valid():
        data = form.cleaned_data
        text = form.cleaned_data.get("text")

    return render(request, 'app/forms.html', {'form': form, 'data': data, 'text': text})
