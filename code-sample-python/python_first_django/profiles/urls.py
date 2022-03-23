from django.urls import path

from profiles import views

urlpatterns = [
    path('user/', views.user),
    path('admin/', views.admin),
]