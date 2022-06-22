from django.db import models
from django.db.models import Model
from django_prometheus.models import ExportModelOperationsMixin


class MyModel(ExportModelOperationsMixin("MyModel"), Model):
    text = models.TextField("text", blank=True, null=True)
