from django.shortcuts import render
from django.http import HttpResponse, HttpRequest


# Create your views here.

def index(request: HttpRequest) -> HttpResponse:
    return HttpResponse("Hello World!")


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
