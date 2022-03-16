if __name__ == '__main__':
    print("lists and tuples")

    a = list(range(0, 10))
    print(a)
    print("****")

    list_a = [1, 2, 3]
    list_b = [4, 5, 6]
    print(list_a + list_b)
    print("****")

    list_b.extend([7, 8, 9])
    print(list_b)

    print("****")
    list_a.append(4)
    list_a.append(5)
    print(list_a)
    print("****")

    # adds the element at the end because index 8 does not exist
    list_a.insert(8, 6)
    print(list_a)
    print("****")

    # adds an element at index and move all elements to the right
    list_a.insert(3, 9)
    print(list_a)
    print("****")

    # remove the last element
    list_a.pop()
    print(list_a)
    list_a.pop(3)
    print(list_a)
    print("****")

    print(list_a[2:5])  # print elements from index 2 to index 5 (not inclusive)
    print(list_a[0::2])  # print each second element
    print("****")

    cities = ["London", "Paris", "Los Angeles", "Beirut"]
    print(cities.index("Los Angeles"))  # It is at the 2nd index
    print("Paris" in cities, "Moscow" not in cities)
    print("****")

    list_c = [4, 3, 2, 1]
    print(list_c)
    list_c.sort()
    print(list_c)
    print("****")

    nums = [10, 20, 30, 40, 50]
    nums_double = []

    for n in nums:
        nums_double.append(n * 2)
    print(nums_double)

    # as comprehension
    nums_double = [n * 2 for n in nums]
    print(nums_double)
    print("****")

    nums = [1, 2, 3, 4, 5]
    odd_mult_by_three = [n * 3 for n in nums if n % 2 == 1]
    print(odd_mult_by_three)
    print("****")

    # go over all possible matches with two lists compherension
    list1 = [10, 20, 30, 40, 50]
    list2 = [1, 2, 3, 4, 5]
    sum_of_lists = [n1 + n2 for n1 in list1 for n2 in list2]
    print(sum_of_lists)
    print("****")

    list1 = [1, 2]
    list2 = [10, 20]
    list3 = [100, 200]
    sum_of_lists = [n1 + n2 + n3 for n1 in list1 for n2 in list2 for n3 in list3]
    print(sum_of_lists)
    print("****")

    the_tuple = (1, "str", 'c', [1, 2, "list"])
    print(the_tuple)
    print(the_tuple[3])
    print([1, 2, "list"] in the_tuple)
    print(the_tuple.index("c"), the_tuple.index('c'))

    print("****")