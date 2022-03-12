if __name__ == '__main__':
    print("loops")

    hello = "hello, world"
    for a in hello:
        print(a, end=" ")
    print()
    for i in range(0, len(hello)):
        print(hello[i], end=" ")
    print()
    for i in range(len(hello) - 1, -1, -1):
        print(hello[i], end=" ")
    print()

    for i in range(0, 10, 2):
        print(i, end=" ")
    print()

    for i in range(10, 20):
        if i > 15:
            break
        print(i, end=" ")
    print()

    print("nothing is about to happen 10 times!")
    for i in range(0, 10):
        pass
    print()


    def fib(n):
        fst = 0
        snd = 1

        if n == 1: return fst
        if n == 2: return snd

        iteration = 3

        fib_sum = fst + snd
        while iteration <= n:
            fib_sum = fst + snd
            fst = snd
            snd = fib_sum
            iteration += 1

        return fib_sum

    print(fib(8))  # 0; 1; 1; 2; 3; 5; 8; 13 => 13

