import time

DEFAULT_FMT = '[{elapsed:0.8f}s] {name}({args}) -> {result}'


# Usually you'd have a func defining another func inside and returning it.
# But in this case you have parameters on decorator
# So you have to first define a func with parameter
#   inside it, you define returned function as normal
#   but that internal function is actually only returning the result of the real function's operation + your extra logic
#

def clock(fmt=DEFAULT_FMT):         # <= decorating function with parameter
    def decorate(func):     # <= the actual decorating function which will be returned as a replacement
        def clocked(*_args):        # <= The func taking original arguments
            t0 = time.time()
            _result = func(*_args)
            elapsed = time.time() - t0
            name = func.__name__
            args = ', '.join(repr(arg) for arg in _args)
            result = repr(_result)
            print(fmt.format(**locals()))
            return _result
        return clocked
    return decorate


if __name__ == '__main__':
    @clock()
    def snooze(seconds):
        time.sleep(seconds)


    @clock('{name}: {elapsed}s')
    def snooze_2(seconds):
        time.sleep(seconds)


    @clock('{name}({args}) dt={elapsed:0.3f}s')
    def snooze_3(seconds):
        time.sleep(seconds)

    for i in range(3):
        snooze(.123)
    print()
    for i in range(3):
        snooze_2(0.123)
    print()
    for i in range(3):
        snooze_3(0.123)
