class Rectangle:
    def __init__(self, length, width):
        self.__length = length
        self.__width = width

    def area(self):
        return self.__length * self.__width

    def perimeter(self):
        return 2 * (self.__length + self.__width)


class Student:

    __name = None
    __roll_number = None

    def set_name(self, name):
        self.__name = name

    def get_name(self):
        return self.__name

    def set_roll_number(self, roll_number):
        self.__roll_number = roll_number

    def get_roll_number(self):
        return self.__roll_number

if __name__ == '__main__':
    print("oop hiding")
