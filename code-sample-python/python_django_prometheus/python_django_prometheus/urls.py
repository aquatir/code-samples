from django.contrib import admin
from django.urls import path, include
from rest_framework import routers

from metrics_app import views

router = routers.DefaultRouter()
router.register(r"users", views.UserViewSet)
router.register(r"groups", views.GroupViewSet)

urlpatterns = [
    path("", include(router.urls)),
    path("", include("django_prometheus.urls")),
    path("admin/", admin.site.urls),
    path("app/", include("metrics_app.urls")),
    path("api-auth/", include("rest_framework.urls", namespace="rest_framework")),
]
