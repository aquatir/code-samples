from functools import reduce


def func():
    return reduce(lambda a, acc: a + acc, range(1, 1_000_000))
