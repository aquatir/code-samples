import datetime

from django.contrib.auth.models import User
from django.db import models
from django.utils import timezone


class Post(models.Model):
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    title = models.CharField(max_length=200)
    text = models.TextField()
    created_date = models.DateTimeField(default=timezone.now)
    published_date = models.DateTimeField(blank=True, null=True)


class Topic(models.Model):
    top_name = models.CharField(max_length=264, unique=True)

    def __str__(self):
        return self.top_name


class Webpage(models.Model):
    topic = models.ForeignKey(Topic, on_delete=models.CASCADE)
    name = models.CharField(max_length=264, unique=True)
    url = models.URLField(unique=True)

    def __str__(self):
        return self.name


class AccessRecord(models.Model):
    name = models.ForeignKey(Webpage, on_delete=models.CASCADE, help_text="the name of web page")
    date = models.DateField(default=timezone.now)
    count = models.IntegerField(default=0)

    def __str__(self):
        return str(self.date)


# One to many relation

class Company(models.Model):
    name = models.CharField(max_length=264, unique=True)
    number_of_employees = models.IntegerField(default=0)

    def __str__(self):
        return self.name


class Employee(models.Model):
    employee_name = models.CharField(max_length=264, unique=True)
    company_name = models.ForeignKey(Company, on_delete=models.CASCADE)
    date_of_birth = models.DateField(default=timezone.now)

    def __str__(self):
        return self.employee_name


class Project(models.Model):
    project_name = models.CharField(max_length=264, unique=True)

    # Many to many mapping
    employee_name = models.ManyToManyField(Employee)

    # One to one mapping
    team_lead = models.OneToOneField(Employee, on_delete=models.CASCADE, related_name='team_lead', default=None,
                                     null=True)

    def __str__(self):
        return self.project_name
