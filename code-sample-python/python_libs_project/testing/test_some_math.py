import sys

import some_math
import unittest


# Run this class only: python3 testing/test_some_math.py
# or python3 -m unittest test_some_math.py
class TestAdd(unittest.TestCase):
    """
    Test the add function from the some_math library
    """

    @classmethod
    def setUpClass(cls) -> None:
        print("run before all tests")

    def setUp(self):
        print("--run before each test")

    def tearDown(self):
        print("--run after each test")

    @classmethod
    def tearDownClass(cls):
        print("run after all tests")

    def test_add_integers(self):
        """
        Test that the addition of two integers returns the correct total
        """
        print("---running test_add_integers")
        result = some_math.add(1, 2)
        self.assertEqual(result, 3)

    def test_add_floats(self):
        """
        Test that the addition of two floats returns the correct result
        """
        print("---running test_add_floats")
        result = some_math.add(10.5, 2)
        self.assertEqual(result, 12.5)

    @unittest.skip('Skip this test')
    def test_add_strings(self):
        """
        Test the addition of two strings returns the two string as one
        concatenated string
        """
        print("---running test_add_strings")
        result = some_math.add('abc', 'def')
        self.assertEqual(result, 'abcdef')

    @unittest.skipUnless(sys.platform.startswith("win"), "requires Windows")
    def test_adding_on_windows(self):
        result = some_math.add(1, 2)
        self.assertEqual(result, 3)

    @unittest.skipUnless(sys.platform.startswith("darwin"), "requires darwin")
    def test_adding_on_darwin(self):
        result = some_math.add(1, 2)
        self.assertEqual(result, 3)


class TestMultiply(unittest.TestCase):
    """
    Test the multiply function from the mymath library
    """

    def test_subtract_integers(self):
        """
        Test that multiplying integers returns the correct result
        """
        result = some_math.multiply(5, 50)
        self.assertEqual(result, 250)


class TestDivide(unittest.TestCase):
    """
    Test the divide function from the mymath library
    """

    def test_divide_by_zero(self):
        """
        Test that multiplying integers returns the correct result
        """
        with self.assertRaises(ZeroDivisionError):
            result = some_math.divide(8, 0)


if __name__ == '__main__':
    unittest.main()
