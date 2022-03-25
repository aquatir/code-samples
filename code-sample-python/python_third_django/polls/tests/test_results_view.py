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
        question = create_question(question_text="test_question", days=0)
        question.choice_set.create(choice_text="test_choice_1", votes=1)
        question.choice_set.create(choice_text="test_choice_2", votes=2)

        url = reverse('polls:results', args=(question.id,))
        response = self.client.get(url)
        self.assertEqual(response.status_code, 200)
        self.assertContains(response, "test_question")
        self.assertContains(response, "test_choice_1")
        self.assertContains(response, "1 vote")
        self.assertContains(response, "test_choice_2")
        self.assertContains(response, "2 votes")
