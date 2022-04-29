class ClassWithValue:

    def __init__(self, value):
        self.value = value

    def __add__(self, other):
        return ClassWithValue(self.value + other.value)

    def __str__(self):
        return f'ClassWithValue({self.value})'


if __name__ == '__main__':
    a = ClassWithValue(5)
    b = ClassWithValue(10)

    print(a)
    print(b)
    print(a + b)