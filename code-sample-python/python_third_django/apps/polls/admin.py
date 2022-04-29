from django.contrib import admin

# Register your models here.
from apps.polls.models import Question, Choice


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

    # By default, Django admin would display the result of __str__ for object.
    # With this option, it would display the required fields as a table
    # you can even add functions there as in 'was_published_recently'
    # this also allow you so sort stuff (but not using the arbitrary function like 'was_published_recently')
    list_display = ('question_text', 'pub_date', 'was_published_recently')

    # This adds an extra filtering sidebar on the right of the admin panel
    list_filter = ['pub_date']

    # Adds an ability to search by text on the specified field
    search_fields = ['question_text']

admin.site.register(Question, QuestionAdmin)
