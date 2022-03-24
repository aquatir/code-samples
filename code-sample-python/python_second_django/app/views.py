from django.core.exceptions import ValidationError
from django.http import HttpRequest, HttpResponse
from django.shortcuts import render
from django.utils.translation import gettext

from app.forms import TestForm, CompanyModelForm
from app.models import Company


def hello(request: HttpRequest) -> HttpResponse:
    return HttpResponse("hello")


def index(request: HttpRequest) -> HttpResponse:

    default_company = Company.objects.get(name='default company')
    if not default_company:
        print("creating default company")
        default_company = Company.objects.create(name="default company", number_of_employees=0)
        default_company.save()
    else:
        print("default company already exist")

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


def company(request):
    form = CompanyModelForm(request.POST)

    if form.is_valid():
        print("saving")
        form.save()

    print("rendering form")
    return render(request, 'app/company.html', {'form': form})
