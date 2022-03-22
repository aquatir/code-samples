import logging
import subprocess
import threading
import time
from queue import Queue, Empty


class MyThread(threading.Thread):

    def __init__(self, number, logger):
        threading.Thread.__init__(self)
        self.number = number
        self.logger = logger

    def run(self):
        """
        Run the thread
        """
        logger.debug('Calling doubler')
        doubler(self.number, self.logger)


def get_logger():
    logger = logging.getLogger("threading_example")
    logger.setLevel(logging.DEBUG)

    fh = logging.FileHandler("threading.log")
    fmt = '%(asctime)s - %(threadName)s - %(levelname)s - %(message)s'
    formatter = logging.Formatter(fmt)
    fh.setFormatter(formatter)

    logger.addHandler(fh)
    return logger


def doubler(number, logger):
    """
    A function that can be used by a thread
    """
    logger.debug('doubler function executing')
    result = number * 2
    logger.debug('doubler function ended with: {}'.format(
        result))


def creator(data, q):
    """
    Creates data to be consumed and waits for the consumer
    to finish processing
    """
    print('Creating data and putting it on the queue')
    for item in data:
        evt = threading.Event()
        q.put((item, evt))

        print('Waiting for data to be doubled')
        evt.wait()
    print("closing creator")


def my_consumer(q: Queue):
    """
    Consumes some data and works on it

    In this case, all it does is double the input
    """
    global should_stop
    try:
        while True:
            data, evt = q.get(timeout=5)
            print('data found to be processed: {}'.format(data))
            processed = data * 2
            print(processed)
            evt.set()
            q.task_done()
    except Empty:
        print("closing consumer because not data is left")


total = 0
update_total_lock = threading.Lock()


def update_total(amount):
    """
    Updates the total by the given amount
    """
    global total
    with update_total_lock:
        total += amount
    print(total)


if __name__ == '__main__':
    logger = get_logger()
    threads = []
    for i in range(5):
        my_thread = MyThread(i, logger)  # this creates iterable
        my_thread.start()
        threads.append(my_thread)

    #  will join threads from first to last, which is bad in general but okay in this case
    for thread in threads:
        thread.join()
    print("all threads finished")
    print("****")

    #  using a lock
    threads = []
    for i in range(10):
        my_thread = threading.Thread(
            target=update_total, args=(5,))
        my_thread.start()
        threads.append(my_thread)

    for thread in threads:
        thread.join()
    print("all threads finished")
    print(f"total: {total}")
    print("****")

    #  using a timer to kill 'ping' process after 5 seconds
    kill = lambda process: process.kill()
    cmd = ['ping', 'www.google.com']
    ping = subprocess.Popen(
        cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    my_timer = threading.Timer(5, kill, [ping])

    try:
        my_timer.start()
        stdout, stderr = ping.communicate()
    finally:
        my_timer.cancel()

    print(str(stdout))
    print("****")

    # queue with events

    q = Queue()
    data = [5, 10, 13, -1]
    thread_one = threading.Thread(target=creator, args=(data, q))
    thread_two = threading.Thread(target=my_consumer, args=(q,))
    thread_one.start()
    thread_two.start()

    q.join()
    print("****")
