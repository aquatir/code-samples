import logging
import threading


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


if __name__ == '__main__':
    logger = get_logger()
    threads = []
    for i in range(5):
        my_thread = MyThread(i,logger)  # this creates iterable
        my_thread.start()
        threads.append(my_thread)

    #  will join threads from first to last, which is bad in general but okay in this case
    for thread in threads:
        thread.join()
    print("all threads finished")
