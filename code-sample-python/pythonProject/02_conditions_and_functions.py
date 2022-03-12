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
