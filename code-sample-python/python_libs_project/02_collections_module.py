import string
from collections import ChainMap, Counter, defaultdict, deque, namedtuple, OrderedDict

if __name__ == '__main__':
    print("collections module running")

    #
    # ChainMap
    #
    car_parts = {'hood': 500, 'engine': 5000, 'front_door': 750}
    car_options = {'A/C': 1000, 'Turbo': 2500, 'rollbar': 300}
    car_accessories = {'cover': 100, 'hood_ornament': 150, 'seat_cover': 99}
    car_pricing = ChainMap(car_accessories, car_options, car_parts)
    print(car_pricing['hood'], car_pricing['cover'], car_pricing['A/C'])
    print("****")

    app_defaults = {'username': 'app_admin', 'password': 'app_password'}

    env_default = {'username': 'default_env_user', 'password': 'default_env_password'}
    user_inputs = {'username': 'default_user'}

    chained = ChainMap(user_inputs, env_default, app_defaults)
    print(chained['username'], chained['password'])
    print("****")

    #
    # Counter aka Multiset that is set which allows multiple elements
    #
    counter = Counter('superfluous')
    print(counter)
    print(counter['u'])
    counter = Counter(["hello", "world", "hello"])
    print(counter)
    print(counter["hello"], counter["world"])
    print("****")

    # ask for most common element
    print(counter.most_common(1))

    # also subtract one counter from another
    freq_a = Counter("abcde")
    freq_b = Counter("edcba")
    freq_a.subtract(freq_b)
    print(freq_a.most_common(1)[0][1] == 0)  # most common element is met 0 times => no elements left
    print("****")

    #
    # defaultdict
    #
    sentence = "The red for jumped over the fence and ran to the zoo for food"
    words = sentence.split(' ')

    d = defaultdict(int)
    for word in words:
        d[word] += 1
    print(d)

    my_list = [(1234, 100.23), (345, 10.45), (1234, 75.00),
               (345, 222.66), (678, 300.25), (1234, 35.67)]

    d = defaultdict(list)
    for acct_num, value in my_list:
        d[acct_num].append(value)
    print(d)

    animal = defaultdict(lambda: "Monkey")
    animal['Sam'] = 'Tiger'
    print(animal['Nick'])
    print(animal)
    print("****")

    #
    # deque
    #
    d = deque(string.ascii_lowercase)
    for letter in d:
        print(letter, end=", ")
    print("")
    d = deque([4, 5, 6])
    d.appendleft(3)
    d.append(7)
    print(d)
    d.rotate(1)  # move elements to the right
    print(d)
    d.rotate(-1)  # move elements to the left
    print(d)
    print("****")

    #
    # namedtuple
    #
    Parts = namedtuple('Parts', 'id_num desc cost amount')
    auto_parts = Parts(id_num='1234', desc='Ford Engine',
                       cost=1200.00, amount=10)
    print(auto_parts)
    print(auto_parts.id_num)  # don't need to track individual element's index in tuple. Can use the name

    # absolute magic but essentially does the same thing as code above
    Parts = {'id_num': '1234', 'desc': 'Ford Engine',
             'cost': 1200.00, 'amount': 10}
    parts = namedtuple('Parts', Parts.keys())(**Parts)
    print(parts)

    keys_as_tuple = namedtuple('Parts', Parts.keys())
    parts = keys_as_tuple(**Parts)
    print(parts)
    print("****")
    #
    # OrderedDict
    #
    d = {'banana': 3, 'apple': 4, 'pear': 1, 'orange': 2}
    new_d = OrderedDict(sorted(d.items()))
    print(new_d)
    for key in new_d:
        print(key, new_d[key], end="; ")
    print()
    print("****")