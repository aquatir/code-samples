from types import MappingProxyType

if __name__ == '__main__':

    print("multiple ways to create a dict:")
    a = dict(one=1, two=2, three=3)
    b = {'one': 1, 'two': 2, 'three': 3}
    c = dict(zip(['one', 'two', 'three'], [1, 2, 3]))
    d = dict([('two', 2), ('one', 1), ('three', 3)])
    e = dict({'three': 3, 'one': 1, 'two': 2})
    print(a == b == c == d == e)
    print("dict comprehension:")
    DIAL_CODES = [
        (86, 'China'),
        (91, 'India'),
        (1, 'United States'),
        (62, 'Indonesia'),
        (55, 'Brazil'),
        (92, 'Pakistan'),
        (880, 'Bangladesh'),
        (234, 'Nigeria'),
        (7, 'Russia'),
        (81, 'Japan'),
    ]
    country_code: dict = {country: code for code, country in DIAL_CODES}
    print(country_code)
    print("Mapping proxy: can view dict, but can't change it")

    d = {'a': 1, 'b': 2}
    d_proxy = MappingProxyType(d)
    print(d_proxy)
    print(d_proxy['a'], d_proxy['b'])
    try:
        d_proxy['c'] = 'new_value'
    except TypeError as e:
        print(f'failed to update mapping proxy. Ex: {e}')

    print()
