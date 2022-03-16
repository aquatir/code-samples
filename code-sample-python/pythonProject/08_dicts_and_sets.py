if __name__ == '__main__':
    print("dicts and sets")

    empty_dict = {}  # Empty dictionary
    print(empty_dict)

    phone_book = {"Batman": 468426,
                  "Cersei": 237734,
                  "Ghostbusters": 44678}
    print(phone_book)
    print("****")

    # can create a dict from the list of tuples
    other_dict = dict([(1, "one"), (2, "two"), (3, "three")])
    print(other_dict)

    # if the key is string we can use this form
    other_dict = dict(one=1, two=2, three=3)
    print(other_dict)
    print(other_dict.get("one"), other_dict.get("two"), other_dict.get("four"))
    print("****")

    # add new value or update
    other_dict["four"] = 4
    print(other_dict.get("four"))
    other_dict["four"] = 44
    print(other_dict.get("four"))

    popped_value = other_dict.pop("four")
    print(other_dict, popped_value)
    del other_dict["three"]
    print(other_dict)
    print(len(other_dict))
    print("one" in other_dict)
    print("****")

    other_numbers = dict(five=5, six=6, seven=7)
    other_dict.update(other_numbers)
    print(other_dict, other_numbers)

    print("****")
    print(other_dict.items())

    # dict comprehension
    houses = {1: "Gryffindor", 2: "Slytherin", 3: "Hufflepuff", 4: "Ravenclaw"}
    new_houses = {n**2: house + "!" for (n, house) in houses.items()}
    print(houses)
    print(new_houses)

    print("****")

