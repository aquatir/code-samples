import math
from collections import namedtuple
from functools import wraps
from inspect import getgeneratorstate


def coroutine():
    print('Started... Waiting for the next send command')
    x = yield  # First yield
    print('Received', x, ' Waiting for the nxt send command')

    y = yield  # Second yield
    print('Received', y, ' Exiting with StopIteration exception')


def subgenerator():
    value = yield
    return value * value


def generator():
    term = yield from subgenerator()
    term += term

    return term


def maximum():
    result = - math.inf

    while True:
        value = yield result  # Receiving values

        if value > result:  # Deciding on maximum
            result = value


def coroutine_primer(func):
    @wraps(func)
    def primer(*args, **kwargs):
        generator = func(*args, **kwargs)
        next(generator)
        return generator

    return primer


@coroutine_primer
def minimum():
    result = + math.inf

    while True:
        value = yield result  # Receiving values

        if value < result:  # Deciding on minimum
            result = value


@coroutine_primer
def coroutine_stub():
    # result = + math.inf
    value1 = yield
    value2 = yield
    print(f'value from coroutine_stub: {value1}, {value2}')


Result = namedtuple('Result', 'count average')


@coroutine_primer
def averager():
    total = 0.0
    count = 0
    average = None
    while True:
        term = yield
        if term is None:
            break
        total += term
        count += 1
        average = total / count
    return Result(count, average)


if __name__ == '__main__':
    print("coroutines revisited running")
    print("****")

    cr = coroutine()
    print(getgeneratorstate(cr))  # Not activated yet = GEN_CREATED

    next(cr)
    print(getgeneratorstate(cr))  # Suspended coroutine GEN_SUSPENDED

    cr.send('Educative')
    print(getgeneratorstate(cr))  # Suspended coroutine = GEN_SUSPENDED

    try:
        cr.send('Python')
    except StopIteration:
        print(getgeneratorstate(cr))  # Coroutine terminated = GEN_CLOSED
    else:
        print("exception is not StopIteration")  # never called

    print("****")

    cr = generator()
    next(cr)
    try:
        cr.send(2)
    except StopIteration as ex:
        print(ex)  # would print 8.

    print("****")
    print("****")
    print("Computing running maximum")
    coroutine = maximum()
    next(coroutine)

    # Sending values
    print(coroutine.send(1))
    print(coroutine.send(7))
    print(coroutine.send(3))
    print(coroutine.send(10))
    print(coroutine.send(5))
    print(coroutine.send(8))
    print(coroutine.send(12))

    coroutine.close()  # Closing coroutine
    print("****")

    print("Computing running min with coroutine primer")
    min = minimum()
    print(min.send(12))
    print(min.send(7))
    print(min.send(9))
    print(min.send(10))
    print(min.send(5))
    print(min.send(8))
    print(min.send(12))
    print(min.send(2))
    min.close()

    print("****")
    coro_avg = averager()
    coro_avg.send(10)
    coro_avg.send(30)
    coro_avg.send(6.5)
    try:
        coro_avg.send(None)
    except StopIteration as ex:
        print(ex)

    print("printing kekw")
    print("****")
    k = coroutine_stub()
    k.send(4)
    try:
        print(f'k.send(5) result: {k.send(5)}')
    except StopIteration as ex:
        print(f'printing k.send(5) ex: {ex}')
