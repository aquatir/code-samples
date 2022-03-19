import urllib.error
import urllib.request
from decimal import Decimal
from functools import lru_cache, partial, singledispatch


# Use LRU cache. Could be typed. Could also use simplier @cache which NEVER evicts items
@lru_cache(maxsize=24)
# @cache
def get_webpage(module):
    """
    Gets the specified Python module web page
    """
    webpage = "https://docs.python.org/3/library/{}.html".format(module)
    try:
        with urllib.request.urlopen(webpage) as request:
            return request.read()
    except urllib.error.HTTPError:
        return None


def add(x, y):
    return x + y


# Typed function overloading in python:
# Create a singledispatch function and create a bunch of functions with overloaded behaviour
# Only overloads on first argument type
@singledispatch
def add(a, b):
    raise NotImplementedError(f"Unsupported type: {type(a)}. Supported: {add.registry.keys()}")


# Can use the same function to dispatch on multiple types
@add.register(int)
@add.register(float)
@add.register(Decimal)
def _(a, b):
    print("First argument is of type ", type(a))
    return a + b


@add.register(str)
def _(a, b):
    print("First argument is of type ", type(a))
    return a + b


@add.register(list)
def _(a, b):
    print("First argument is of type ", type(a))
    return a + b


if __name__ == '__main__':
    print("functools module starting")
    modules = ['functools', 'collections', 'os', 'sys', 'kekw', 'os', 'os', 'os', 'sys', 'sys', 'sys']
    for module in modules:
        page = get_webpage(module)
        if page:
            print("{} found".format(module))
        else:
            print("{} NOT found".format(module))
    print("****")

    # partially apply first argument to the function returning new function. Kind of like currying.
    p_add = partial(add, 2)
    print(p_add(4), p_add(6), p_add(8))
    print("****")

    print(add(1, 2), add(1.2, 3.3), add("hello,", " world"), add([1, 2], [3, 4]))
    # print(add({1}, {2}))  # will throw NotImplementedError
    print("****")
