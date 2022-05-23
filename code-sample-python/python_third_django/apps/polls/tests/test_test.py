from django.test import TestCase


class SomeTest(TestCase):
    def setUp(self):
        super().setUp()
        print("-- before each test: setUp")

    def tearDown(self):
        super().tearDown()
        print("-- after each test: tearDown")

    @classmethod
    def setUpClass(cls):
        super().setUpClass()
        print("before class: setUpClass")

    @classmethod
    def tearDownClass(cls):
        super().tearDownClass()
        print("after class: tearDownClass")

    @classmethod
    def setUpTestData(cls):
        print("before class: setUpTestData")

    def test_true(self):
        self.assertTrue(True)

    def test_false(self):
        self.assertFalse(False)
