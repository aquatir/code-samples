from django.contrib import admin

# Register your models here.
from polls.models import Question, Choice


# (admin.StackedInline) takes a lot of place, but it looks obvious
# (admin.TabularInline) is the opposite. It also allows deliting multiple rows
class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 1


class QuestionAdmin(admin.ModelAdmin):
    # make pub_date come before question_test in admin panel during creation
    # generally the order follows the order at which the fields are created normally

    # fields = ['pub_date', 'question_text']

    # You can also split the data into multiple headings
    fieldsets = [
        (None, {'fields': ['question_text']}),
        ('Date information', {'fields': ['pub_date']}),
    ]

    # Allow creating multiple choice options when creating a question
    # It will display 1 unfilled entry, but you can also add some extras
    inlines = [ChoiceInline]


admin.site.register(Question, QuestionAdmin)
