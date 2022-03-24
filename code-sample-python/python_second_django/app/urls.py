from django.urls import path

from app import views

urlpatterns = [
    path('hello/', views.hello),
    path('', views.index),
    path('forms/', views.forms, name="form"),
]
