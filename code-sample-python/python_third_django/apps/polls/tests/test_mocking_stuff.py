from dataclasses import dataclass
from unittest import mock
from unittest.mock import MagicMock

from django.test import SimpleTestCase


# Data returned by external service client
@dataclass
class Result:
    name: str
    amount: int


# A client with a method we would like to mock
class ExternalServiceClient:

    def call_something(self, name: str, amount: int) -> Result:
        raise Exception('external call not available')


# service which we are trying to test
class ServiceUnderTest:

    def __init__(self, ext_service: ExternalServiceClient):
        self.external_service = ext_service

    def function_making_external_call(self, name: str, amount: int) -> Result:
        res = self.external_service.call_something(name, amount)
        return res


class MockExampleTest(SimpleTestCase):

    def setUp(self):
        self.service_under_test = ServiceUnderTest(ExternalServiceClient())

    # instruct function call_something inside ExternalServiceClient
    # to return a pre-defined value
    @mock.patch.object(ExternalServiceClient, 'call_something', return_value=Result('Pavel', 100), autospec=True)
    def test_service(self, mocked_call_something: MagicMock):

        # mocked_call_something is now a mock object of type MagicMock

        res = self.service_under_test.function_making_external_call('Pavel', 100)

        expected = Result('Pavel', 100)
        self.assertEqual(expected, res)
        mocked_call_something.assert_called_once_with(
            self.service_under_test.external_service,
            'Pavel',
            100
        )
