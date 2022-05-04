import bisect
from collections import namedtuple

if __name__ == '__main__':
    a = 5
    b = 10

    print('Swap using tuple unpacking')
    print(a, b)
    a, b = b, a
    print(a, b)

    print('Another tuple unpacking example')
    print(divmod(21, 5))
    t = (21, 5)
    print(divmod(*t))
    print('****')

    print('Grabbing the rest of the values')
    a, b, *rest = range(5)
    print(a, b, rest)
    print('****')

    print('You can even grab something inbetween or at the beginning')
    a, *rest, b = range(5)
    print(a, rest, b)
    print('****')

    *rest, a, b = range(5)
    print(rest, a, b)
    print('****')

    print('Named tuples also support unpacking and grabbing')
    City = namedtuple('City', 'name country population')
    moscow = City('Moscow', 'Russia', 147000000)
    print(moscow)
    print(City._fields)

    name, country, population = moscow
    print(name, country, population)
    name, *rest = moscow
    print(name, rest)
    print(moscow._asdict())  # can print tuple as dict

    # can even create named tuples from normal tuples
    dubai_data = ('Dubai', 'UAE', 5000000)
    dubai = City._make(dubai_data)
    print(dubai)

    print('****')
    print('bisect could be used for fancy table lookup, e.g covert test scores to letter values')


    def grade(score, breakpoints=[60, 70, 80, 90], grades='FDCBA'):
        i = bisect.bisect(breakpoints, score)
        return grades[i]


    grades = [grade(score) for score in [33, 99, 77, 70, 89, 90, 100]]
    print(grades)
    print('****')

    print()
    print("bisect.insort inserts an item while preserving existing sort")
    a = [1, 4, 5, 7, 9]
    print(a)
    bisect.insort(a, 6)
    print(a)
