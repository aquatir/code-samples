import random
import time


def func():
    try:
        1 / 0
    except ZeroDivisionError:
        pass


# custom timer. Probably not as good as timeit module
def timerfunc(func):
    """
    A timer decorator
    """

    def function_timer(*args, **kwargs):
        """
        A nested function for timing other functions
        """
        start = time.time()
        value = func(*args, **kwargs)
        end = time.time()
        runtime = end - start
        msg = "The runtime for {func} took {time} seconds to complete"
        print(msg.format(func=func.__name__,
                         time=runtime))
        return value

    return function_timer


@timerfunc
def long_runner():
    long_running_function()


def long_running_function():
    for x in range(2):
        sleep_time = random.choice(range(0, 2))
        time.sleep(sleep_time)


# Yet another way to create a custom timer
class MyTimer:

    def __init__(self):
        self.start = time.time()

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        end = time.time()
        runtime = end - self.start
        msg = 'The function took {time} seconds to complete'
        print(msg.format(time=runtime))


if __name__ == '__main__':
    print("benchmarking running")

    import timeit
    setup = "from __main__ import func"
    print(timeit.timeit("func()", setup=setup))
    print("****")

    long_runner()
    print("****")

    with MyTimer():
        long_running_function()
    print("****")

    #  using cProfile
    import cProfile
    cProfile.run("[x for x in range(150_000)]")
    print("****")
