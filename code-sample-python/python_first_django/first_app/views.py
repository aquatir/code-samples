from django.shortcuts import render
from django.http import HttpResponse, HttpRequest


def index(request: HttpRequest) -> HttpResponse:
    model = dict(name="Ivan", surname="Ivanov", values=[1, 2, 3])
    return render(request, 'index.html', context=model)


def shows(request: HttpRequest) -> HttpResponse:
    tv_shows_list = {"tv_shows": {'Game of Thrones': '9.3', 'Blacklist': '8', 'Suits': '8.5', 'The Witcher': '8.5'}}
    return render(request, 'shows.html', context=tv_shows_list)


def home(request: HttpRequest) -> HttpResponse:
    return HttpResponse("Welcome to home page!")


def show_age(request: HttpRequest, age: int) -> HttpResponse:
    return HttpResponse("I am %s years old." % age)


def show_name(request: HttpRequest, name: str) -> HttpResponse:
    return HttpResponse("My name is %s." % name)


def even_or_odd(request: HttpRequest, num: int) -> HttpResponse:
    if num % 2 == 0:
        output = "%s is an even number." % num
    else:
        output = "%s is an odd number." % num
    return HttpResponse(output)


def base_home(request):
    return render(request, 'home.html')


def base_search(request):
    return render(request, 'search.html')


def base_about(request):
    return render(request, 'about.html')
