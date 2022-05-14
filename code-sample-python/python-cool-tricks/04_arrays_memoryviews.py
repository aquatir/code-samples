from array import array
from random import random

if __name__ == '__main__':
    print("Generate 10 million float-point numbers, save them in file, retrieve back")
    print("Generation is the longest operation here, saving and retrieving is trivially fast")

    floats = array('d', (random() for i in range(10 ** 7)))
    print(floats[-1])
    print("generated...")

    with open('floats.bin', 'wb') as fp:
        floats.tofile(fp)
    print("saved...")

    floats2 = array('d')
    with open('floats.bin', 'rb') as fp:
        floats2.fromfile(fp, 10 ** 7)
    print("retrieving...")

    print(floats2[-1])
    print(floats2 == floats)
    print("****")
    print()

    print("memory view allows you to share memory between data structures")
    numbers = array('h', [-2, -1, 0, 1, 2])
    memv = memoryview(numbers)
    print(len(memv))
    print(memv[0])

    memv_oct = memv.cast('B')  # this will create a binary representation of array
    print(memv_oct.tolist())

    # change byte # 5. Bytes 4 and 5 are representing 0 from numbers.
    # changing byte 5 to value 4 means changing the upper 8 bytes from 0 to 4.
    # that will produce binary 00000100_00000000 which is 1024
    memv_oct[5] = 4
    print(numbers)

    print("****")
    print()

