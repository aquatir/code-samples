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


if __name__ == '__main__':
    print("stuff running")

    b()
