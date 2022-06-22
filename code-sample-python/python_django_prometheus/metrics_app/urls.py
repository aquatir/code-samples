from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("ex", views.ex, name="exceptions"),
    path("model", views.model, name="model"),
    path("hist", views.hist, name="histogram"),
]
