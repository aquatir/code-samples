def a(func):
    def wrapped(*args, **kwargs):
        print("a before")
        value = func(*args, **kwargs)
        print("a after")
        return value

    return wrapped


#  call to b will be wrapped by a call to a
@a
def b():
    print("b")


def counter():
    num = 0

    # Use num from above by using nonlocal
    def incrementer():
        nonlocal num
        num += 1
        return num

    return incrementer


if __name__ == '__main__':
    print("stuff running")
    b()
    c = counter()
    print(c(), c(), c(), c())
