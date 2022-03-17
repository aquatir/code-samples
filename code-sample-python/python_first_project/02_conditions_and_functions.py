import random


def print_something():
    print("something")


def print_value(a, b="default"):
    print(f"value 'a' is: {a}; value 'b' is : {b}")


if __name__ == '__main__':

    a = random.randint(0, 10)
    print(f"a: {a}")
    if a > 5:
        print("more than 5")
    elif a == 5:
        print("equal to 5")
    else:
        print("less than 5")

    # elvis operator but other way around
    b = "more than 5" if a > 5 else "less or equal to 5"
    print(b)

    print_something()
    print_value("kekw")
    print_value(1213)
    print_value("aaa", "bbb")

    some_str = "this is a string"
    print(some_str.find("is"), some_str.find("is", 3), some_str.find("hello"))

    a_string = "hello, world"
    a_string.replace("hello", "goodbye")
    print(a_string.lower())
    a_string = a_string.replace("hello", "goodbye")
    print(a_string.upper())
    print(";".join(["a", "b", "c", "d"]))

    string1 = "Learn Python {version} at {cname}".format(version=3, cname="Educative")
    string2 = "Learn Python {0} at {1}".format(3, "Educative")
    string3 = "Learn Python {} at {}".format(3, "Educative")
    print(string1, string2, string3)

    # violates PET-8: E731
    triple = lambda x: x * 3


    def triple2(x):
        return x * 3


    print(triple(3), triple(5), triple(10))
    print(triple2(3), triple2(5), triple2(10))

    sum_three = lambda x1, x2, x3: x1 + x2 + x3
    print(sum_three(1, 2, 3), sum_three(3, 3, 3), sum_three(1, 10, 100))

    print(list(map(lambda x: x * x, [1, 2, 3])))
    print(list(filter(lambda x: x % 2 == 0, [1, 2, 3, 4])))


    def factorial(n):
        if n == 0 or n == 1:
            return 1
        if n < 0:
            return -1

        # cur = 1
        # while n > 0:
        #     cur = cur * n
        #     n -= 1

        return n * factorial(n - 1)


    print(factorial(5))
