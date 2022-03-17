from abc import ABC, abstractmethod


class Com:
    def __init__(self, real=0, imag=0):
        self.real = real
        self.imag = imag

    def __add__(self, other):  # overloading the `+` operator
        temp = Com(self.real + other.real, self.imag + other.imag)
        return temp

    def __sub__(self, other):  # overloading the `-` operator
        temp = Com(self.real - other.real, self.imag - other.imag)
        return temp

    def __str__(self):  # overloading printing
        return f"({self.real}{'+' if self.imag > 0 else '-'}{abs(self.imag)}i)"


class Dog:
    def sound(self):
        print("Woof woof")


class Cat:
    def sound(self):
        print("Meow meow")


class AnimalSound:

    # duck typing
    def sound(self, animal):
        animal.sound()


class Shape(ABC):  # Shape is a child class of ABC
    @abstractmethod
    def area(self):
        pass

    @abstractmethod
    def perimeter(self):
        pass


class Square(Shape):
    def __init__(self, length):
        self.length = length

    def area(self):
        return self.length * self.length

    def perimeter(self):
        return 4 * self.length

    def __str__(self):
        return f"square: {self.length}"


if __name__ == '__main__':
    a = Com(5, 7)
    b = Com(10, 3)
    print(a + b)
    print(Com(3, -3))
    print("****")

    sound = AnimalSound()
    dog = Dog()
    cat = Cat()

    sound.sound(dog)
    sound.sound(cat)
    print("****")

    shape = Square(4)
    print(shape)

    print("****")
