from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("ex", views.ex, name="exceptions"),
    path("hist", views.hist, name="histogram"),
]
