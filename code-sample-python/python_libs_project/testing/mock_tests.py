from unittest.mock import Mock, create_autospec

my_mock = Mock()
my_mock.__str__ = Mock(return_value='Mocking')

print(str(my_mock))


class TestClass:
    pass


cls = TestClass()
cls.method = Mock(return_value='mocking is fun')
print(cls.method(1, 2, 3))
# 'mocking is fun'

cls.method.assert_called_once_with(1, 2, 3)


def my_side_effect():
    print('Updating database!')


def main():
    mock = Mock(side_effect=my_side_effect)
    mock()


main()


def add(a, b):
    return a + b


# automatically created a mocked function from real 'add' function
mocked_func = create_autospec(add, return_value=10)
print(mocked_func(1, 2))
