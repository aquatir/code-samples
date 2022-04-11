if __name__ == '__main__':

    print('You can assign slices to variables and then use them inside an array')

    # id = 4 symbols, age = 2, name = 20
    invoice = """
    id age name 
    1111 20 Ivan Narkoman
    2222 40 Privet Medved
    3333 60 Hello World
    """

    ID = slice(0, 4)
    AGE = slice(4, 6)
    NAME = slice(6, 26)

    line_items = invoice.split('\n')[2:]
    for item in line_items:
        print(item[ID], item[NAME])
    print()
    print('*' * 10)

    print('Slices can be used to assign values')
    l = list(range(1, 10))
    print(l)

    # replace elements on indexes 3, 4, 5 with '20' and '30'.
    l[2:5] = [20, 30]
    print(l)

    # delete elements indexes 5 and 6
    del l[5:7]
    print(l)

    # replace elements from index 3 with a step of 2.
    l[3::2] = [11, 22]
    print(l)

    # replace elements on index 2 with 100 and remove elements with idexes 3 and 4
    l[2:5] = [100]
    print(l)

    print('*' * 10)
    
