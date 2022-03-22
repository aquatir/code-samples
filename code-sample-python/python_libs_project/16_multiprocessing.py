import logging
import multiprocessing
import os
from multiprocessing import Process, current_process


def doubler(number):
    """
    A doubling function that can be used by a process
    """
    result = number * 2
    print('{0} doubled to {1} by process id: {2} name: {3}'.format(
        number, result, os.getpid(), current_process().name))


def simple_doubler(number):
    return number * 2


if __name__ == '__main__':
    print("multiprocessing starting")

    numbers = [5, 10, 15, 20, 25]
    procs = []

    multiprocessing.log_to_stderr()
    logger = multiprocessing.get_logger()
    logger.setLevel(logging.INFO)
    for index, number in enumerate(numbers):
        proc = Process(target=doubler, args=(number,), name=f"Process-{index}-{number}")
        procs.append(proc)
        proc.start()

    for proc in procs:
        proc.join()
    print("****")

    pool = multiprocessing.Pool(processes=3)
    print(pool.map(simple_doubler, numbers))
    print("****")

    result = pool.apply_async(simple_doubler, (25,))
    print(result.get(timeout=1))
    print("****")