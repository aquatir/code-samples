from django.contrib import admin

from app.models import Post, Topic, Webpage, AccessRecord

admin.site.register(Post)
admin.site.register(Topic)
admin.site.register(Webpage)
admin.site.register(AccessRecord)