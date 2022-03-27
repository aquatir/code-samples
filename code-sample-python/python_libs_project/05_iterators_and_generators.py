# Finite Iterator
import operator
from itertools import count, islice, cycle, repeat, accumulate, chain, compress, dropwhile, filterfalse, groupby, \
    takewhile, starmap, tee, zip_longest, combinations, combinations_with_replacement, product, permutations


class MyIterator:
    def __init__(self, letters):
        """
        Constructor
        """
        self.letters = letters
        self.position = 0

    def __iter__(self):
        """
        Returns itself as an iterator
        """
        return self

    def __next__(self):
        """
        Returns the next letter in the sequence or
        raises StopIteration
        """
        if self.position >= len(self.letters):
            raise StopIteration
        letter = self.letters[self.position]
        self.position += 1
        return letter


# 0 1 1 2 3 5 8
class Fibonacci:
    def __init__(self, n):
        self.total = n
        self.cur_iteration = 0
        self.prev = 0

    def __iter__(self):
        return self

    def __next__(self):
        if self.cur_iteration == self.total:
            raise StopIteration
        else:
            if self.cur_iteration == 0:
                self.cur_iteration += 1
                return 0
            elif self.cur_iteration == 1:
                self.prev = 0
                self.cur = 1
                self.cur_iteration += 1
                return 1
            else:
                next = self.prev + self.cur
                self.prev = self.cur
                self.cur = next
                self.cur_iteration += 1
                return next


# Infinite Iterator
class Doubler:
    """
    An infinite iterator
    """

    def __init__(self):
        """
        Constructor
        """
        self.number = 0

    def __iter__(self):
        """
        Returns itself as an iterator
        """
        return self

    def __next__(self):
        """
        Doubles the number each time next is called
        and returns it.
        """
        self.number += 1
        return self.number * self.number


# Double the number forever while next(..) is being called
def doubler_generator(start):
    number = start
    while True:
        yield number
        number *= number


# A finite generator
def silly_generator():
    yield "Python"
    yield "Rocks"
    yield "So do you!"


if __name__ == '__main__':
    my_list = [1, 2, 3, 4, 5]
    list_iterator = iter(my_list)
    print(next(list_iterator), next(list_iterator), next(list_iterator), next(list_iterator), next(list_iterator))

    my_iterator = MyIterator("abcdefg")
    for c in my_iterator:
        print(c, end="; ")
    print()
    print("****")

    doubler = Doubler()
    cntr = 0
    for n in doubler:
        print(n, end="; ")
        if cntr > 100:
            break
        else:
            cntr += 1
    print()
    print("****")

    # generator
    doubler = doubler_generator(2)
    print(next(doubler), next(doubler), next(doubler), next(doubler))

    finite = silly_generator()
    # one extra call will end up with StopIteration exception
    print(f"'{next(finite)}', '{next(finite)}', '{next(finite)}'")
    for value in silly_generator():
        print(f"'{value}'", end=", ")
    print()
    print("****")

    # Infinite iterators with itertools
    for i in count(0, step=2):
        if i > 20:
            break
        print(i, end="; ")
    print()

    # Return only 5 values from iterator
    for i in islice(count(0, step=2), 5):
        print(i, end="; ")
    print()

    # cycle over values
    for c in islice(cycle(["red", "yellow", "green"]), 9):
        print(c, end="; ")
    print()

    cntr = 1
    for value in islice(repeat("ping"), 10):
        print(f"{cntr}.{value}", end="; ")
        cntr += 1
    print()
    print("****")

    # accumulate using a two argument functions
    print(list(accumulate(range(10))))

    # create factorial with accumulate
    mult = lambda x, y: x * y
    print(list(accumulate(range(1, 10), mult)))
    print(list(accumulate(range(1, 10), operator.mul)))  # same things
    print("****")

    # merge multiple iterators one after another
    my_list = []
    foo_bar = ['foo', 'bar']
    numbers = list(range(5))
    cmd = ['ls', '/some/dir']
    my_list = list(chain(foo_bar, cmd, numbers))
    print(my_list)

    #  simpler options
    my_list = foo_bar + cmd + numbers
    print(my_list)

    print("****")
    numbers = [1, 2, 3, 4, 5]
    take_only = [True, False, False, True, True]
    print(list(compress(numbers, take_only)))
    print("****")

    # drops elements while predicate is true, then get all the rest
    print(list(dropwhile(lambda x: x < 5, [1, 4, 3, 2, 6, 3, 2, 1])))
    # take elements until predicate is true
    print(list(takewhile(lambda x: x < 5, [1, 4, 3, 2, 6, 3, 2, 1])))
    # leave out only elements for which the predicate is true, filter out the rest
    print(list(filterfalse(lambda x: x < 5, [1, 2, 3, 4, 5, 6, 3, 2, 1])))
    print("****")
    # Group all values by the first part of tuple. Then print all in one group
    # NOTE: list of tuples should be sorted first in order for the trick to work
    vehicles = [('Ford', 'Taurus'), ('Dodge', 'Durango'),
                ('Chevrolet', 'Cobalt'), ('Ford', 'F150'),
                ('Dodge', 'Charger'), ('Ford', 'GT')]

    sorted_vehicles = sorted(vehicles)

    for key, group in groupby(sorted_vehicles, lambda make: make[0]):
        for make, model in group:
            print('{model} is made by {make}'.format(model=model,
                                                     make=make))
        print(f"**** END OF GROUP {make}***")
    print("****")

    #  apply function to each tuple in the list. Like a map but slightly different
    for item in starmap(operator.add, [(2, 3), (4, 5)]):
        print(item)
    print("****")

    #  create multiple iterators
    data = 'ABCDE'
    iter1, iter2, iter3 = tee(data, 3)
    for c in iter1:
        print(c, end="; ")
    print()
    for c in iter2:
        print(c, end="; ")
    print()
    for c in iter3:
        print(c, end="; ")
    print()
    print("****")

    #  zip the iterators into tuples with default value
    for item in zip_longest('ABCD', 'xy', fillvalue='BLANK'):
        print(item, end="; ")
    print()
    print("****")

    #  normal zip will end as soon as ony of values ends
    print(list(zip('ABCD', 'xy')))
    print("****")

    # create all possible combinations
    print(list(combinations('WXYZ', 2)))

    # create all possible combinations with repeats
    print(list(combinations_with_replacement('WXYZ', 2)))
    print("****")

    # create Cartesian product
    arrays = [(-1, 1), (-3, 3), (-5, 5)]
    print(list(product(*arrays)))

    # could do the same with comprehension
    print([(x, y, z) for x in (-1, 1) for y in (-3, 3) for z in (-5, 5)])
    print("****")

    # permutations
    print(list(permutations('WXYZ', 2)))

    # same with slightly harder comprehension
    print([(x, y) for x in 'WXYZ' for y in 'WXYZ' if x != y])

    f = Fibonacci(7)
    print(next(f), next(f), next(f), next(f), next(f), next(f), next(f))
