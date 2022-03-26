from django.test import TestCase
from django.urls import reverse

from polls.tests.test_utils import create_question


class ResultsViewTests(TestCase):

    def test_result_without_choices_is_shown(self):
        """
        Results can be shown even without choices
        """
        future_question = create_question(question_text='question.', days=0)
        url = reverse('polls:results', args=(future_question.id,))
        response = self.client.get(url)
        self.assertEqual(response.status_code, 200)

    def test_all_choices_are_shown(self):
        """All possible choices and their results must be shown"""

        # given
        question = create_question(question_text="test_question", days=0)
        choice_1 = question.choice_set.create(choice_text="test_choice_1", votes=1)
        choice_2 = question.choice_set.create(choice_text="test_choice_2", votes=2)

        # do
        url = reverse('polls:results', args=(question.id,))
        response = self.client.get(url)
        question_response = response.context['question']
        choices_response = question_response.choice_set.all()

        # expect status is 200
        self.assertEqual(response.status_code, 200)

        # expect the same values are returned
        self.assertEqual(question_response, question)
        self.assertListEqual(list(question.choice_set.all()), list(choices_response))

        # expect template text contains correct values
        self.assertContains(response, "test_choice_1")
        self.assertContains(response, "1 vote")
        self.assertContains(response, "test_choice_2")
        self.assertContains(response, "2 votes")
