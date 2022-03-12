import random

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
