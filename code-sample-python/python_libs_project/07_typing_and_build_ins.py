class Fruit:
    def __init__(self, name, color):
        self.name = name
        self.color = color


# Type hinting works for our own classes
def salad(fruit_one: Fruit, fruit_two: Fruit) -> list:
    print(fruit_one.name)
    print(fruit_two.name)
    return [fruit_one, fruit_two]


def some_function(number: int, name: str) -> None:
    print("%s entered %s" % (name, number))


# Type aliases do work for type hinting
Animal = str


def zoo(animal: Animal, number: int) -> None:
    print("The zoo has %s %s" % (number, animal))


if __name__ == '__main__':
    print("typing and build ins starting")
    print(some_function(13, 'Mike'))
    print(some_function("Mike", 13))  # will work but IDE will show errors here
    print("****")

    f = Fruit('orange', 'orange')
    f2 = Fruit('apple', 'red')
    salad(f, f2)
    print("****")

    zoo('Zebras', 10)
    print("****")

    #  Return True if any element is evaluated to True
    print(any([0, 0, 0, 1]), any(["", 0, False]), any(["", " "]))
    print("****")

    # Remembers both the index and the elements
    for pos, letter in enumerate("abcdefj"):
        print(pos, letter, end="; ")
    print()
    print("****")

    # Evaluate an expression. Caution: don't let user input into eval statement
    value = 55
    print(eval("value * 10"))
    print("****")

    # filters the values
    for item in filter(lambda x: x < 10, [1, 3, 8, 11, 32, 3, 2]):
        print(item, end="; ")
    print()
    print("****")

    # maps one value into another
    print(list(map(lambda x: x * 10, [1, 2, 3, 4, 5])))

    # zips two lists together into a list of tuples.
    # use can then apply functions on that list of tuples
    print(list(zip([1, 2, 3], ['a', 'b', 'c'])))
