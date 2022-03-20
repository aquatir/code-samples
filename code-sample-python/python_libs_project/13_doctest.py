def add(a, b):
    """
    Return the addition of the arguments: a + b

    >>> add(1, 3)
    4
    >>> add(-1, 10)
    9
    >>> add('a', 'b')
    'ab'
    >>> add(1, '2')
    Traceback (most recent call last):
    TypeError: unsupported operand type(s) for +: 'int' and 'str'
    """
    return a + b


if __name__ == '__main__':
    import doctest
    doctest.testmod()
