import sys
import unittest

from . import some_math


# Run with 'pytest'
# You can mix both unittest tests and pytest tests

class TestAdd:
    """
    Test the add function from the some_math library
    """

    @classmethod
    def set_up_class(cls) -> None:
        print("run before all tests")

    def set_up(self):
        print("--run before each test")

    def tear_down(self):
        print("--run after each test")

    @classmethod
    def tear_down_class(cls):
        print("run after all tests")

    def test_add_integers(self):
        """
        Test that the addition of two integers returns the correct total
        """
        print("---running test_add_integers")
        result = some_math.add(1, 2)
        assert result == 3

    def test_add_floats(self):
        """
        Test that the addition of two floats returns the correct result
        """
        print("---running test_add_floats")
        result = some_math.add(10.5, 2)
        assert result == 12.5

    @unittest.skip('Skip this test')
    def test_add_strings(self):
        """
        Test the addition of two strings returns the two string as one
        concatenated string
        """
        print("---running test_add_strings")
        result = some_math.add('abc', 'def')
        assert result == 'abcdef'

    @unittest.skipUnless(sys.platform.startswith("win"), "requires Windows")
    def test_adding_on_windows(self):
        result = some_math.add(1, 2)
        assert result == 3

    @unittest.skipUnless(sys.platform.startswith("darwin"), "requires darwin")
    def test_adding_on_darwin(self):
        result = some_math.add(1, 2)
        assert result == 3
