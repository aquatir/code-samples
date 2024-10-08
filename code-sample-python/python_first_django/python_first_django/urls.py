from first_app import views

"""python_first_django URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
# from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    #    path('admin/', admin.site.urls),
    path('', views.index, name='index'),
    path('shows/', views.shows, name="shows"),
    path('home/', views.home, name="home"),

    path('base/home', views.base_home, name="base home"),
    path('base/about', views.base_about, name="base about"),
    path('base/search', views.base_search, name="base search"),

    path('<int:age>/', views.show_age, name="show_age"),
    path('<str:name>/', views.show_name, name="show_name"),

    path('number/<int:num>/', views.even_or_odd, name="even_or_odd"),

    path('profiles/', include('profiles.urls'))
]
