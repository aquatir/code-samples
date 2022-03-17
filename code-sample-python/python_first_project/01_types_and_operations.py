# This is a sample Python script.

# Press shift+⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press ⌘F8 to toggle the breakpoint.


'''
multi comment
'''

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

    print(5 + 87965789097654895857)
    print(4.234234)

    print(complex(5431242341234123, 23412341234123412))

    f_boolean = False
    t_boolean = True

    print(f_boolean, t_boolean)

    multi_line_string = '''kek
wait
big
string'''

    print(multi_line_string)
    print(len(multi_line_string))

    some_str = "some string"

    print(some_str[-1])  # prints g
    print(some_str[-11])  # prints can only go over the length but can't cycle back

    other_str = "0123456789"

    print("3 to 6: " + other_str[3:7])  # will print 3456 because index 7 is NOT included
    print("all: " + other_str[0:len(other_str)])  # will print everything
    print("step2: " + other_str[0:len(other_str):2])  # print every other number starting from 0
    print("other: " + other_str[::-1])  # print other way around
    print("before 5: " + other_str[:5])  # 01234
    print("after 5: " + other_str[5:])  # 56789

    print(5 ** 2)  # 5^2 == 25
    print(5 / 2)  # 2.5
    print(5 // 2)  # 2
    print(5 % 4)  # 1 (modulo)

    list1 = [1,2,3]
    list2 = [1,2,3]

    print(list1 is list2)  # identity
    print(list1 == list2)  # equal to

    print(True and False, True or False, False or True, not True, not False)  # false, true, true, false, true
    print(10 * True, 10 * False)  # true = 1, false == 0
    print("Hello, world!!!", 1, 3, 4, {5, 5, 5}, end="", sep=" ;*; ")
    print()

    num2 = 20  # Binary Value = 10100
    print(~num2, num2 ^ num2, num2 << 2, num2 >> 2)

    print("kekw " * 3)
    print("Hello" in "Hello, Worlds")
    print([1, "qwer", 12.2, 'v'])

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
