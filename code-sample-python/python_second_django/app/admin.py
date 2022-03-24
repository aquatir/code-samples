from django.contrib import admin

from app.models import Post, Topic, Webpage, AccessRecord, Company, Employee, Project

admin.site.register(Post)
admin.site.register(Topic)
admin.site.register(Webpage)
admin.site.register(AccessRecord)
admin.site.register(Company)
admin.site.register(Employee)
admin.site.register(Project)
