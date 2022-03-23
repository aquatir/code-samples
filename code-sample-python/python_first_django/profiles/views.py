from django.http import HttpRequest, HttpResponse


def user(request: HttpRequest) -> HttpResponse:
    return HttpResponse("Hello, user")


def admin(request: HttpRequest) -> HttpResponse:
    return HttpResponse("Hello, admin")
