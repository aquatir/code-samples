from django.urls import path

from app import views

urlpatterns = [
    path('hello/', views.hello),
    path('', views.index)
]