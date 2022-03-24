from django import forms
from django.core.exceptions import ValidationError
from django.utils.translation import gettext


class SearchForm(forms.Form):
    q = forms.CharField()


INTS_CHOICES = [tuple([x, x]) for x in range(0, 100)]

RADIO_CHOICES = [
    ('signin', 'Sign In'),
    ('signup', 'Sign Up'),
    ('forgotpassword', 'Forgot Password'),
]

CHECKBOX_CHOICES = [
    ('terms', 'Agree to terms and conditions'),
    ('privacy', 'Agree to privacy policy'),

]

YEARS = [x for x in range(1980, 2031)]


class TestForm(forms.Form):
    text = forms.CharField(min_length=7, max_length=50, widget=forms.Textarea, label="Feedback")
    boolean = forms.BooleanField()

    # Different widgets: Textarea instead of text, select, radio select, multicheckbox, select date
    integer = forms.IntegerField(widget=forms.Select(choices=INTS_CHOICES))
    radio_choices = forms.CharField(widget=forms.RadioSelect(choices=RADIO_CHOICES))
    checkbox_choices = forms.CharField(widget=forms.CheckboxSelectMultiple(choices=CHECKBOX_CHOICES))

    # use initial value. Could also do it on views.py level
    email = forms.EmailField(initial="test@test.com")

    date_field = forms.DateField(initial="2020-20-5", widget=forms.SelectDateWidget(years=YEARS))

    # Method will be automatically called to validate 'integer' field. Always use 'clean_%FIELD_NAME%' pattern
    def clean_integer(self):
        integer = self.cleaned_data.get("integer")
        if integer <= 10:
            raise ValidationError(gettext("The integer must be greater than 10"), code='invalid')
        return integer
